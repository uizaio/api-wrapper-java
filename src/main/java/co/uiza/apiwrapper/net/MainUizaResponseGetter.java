package co.uiza.apiwrapper.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import co.uiza.apiwrapper.Uiza;
import co.uiza.apiwrapper.exception.BadRequestException;
import co.uiza.apiwrapper.exception.ClientException;
import co.uiza.apiwrapper.exception.InternalServerException;
import co.uiza.apiwrapper.exception.NotFoundException;
import co.uiza.apiwrapper.exception.ServerException;
import co.uiza.apiwrapper.exception.ServiceUnavailableException;
import co.uiza.apiwrapper.exception.UizaException;
import co.uiza.apiwrapper.exception.UnauthorizedException;
import co.uiza.apiwrapper.exception.UnprocessableException;
import co.uiza.apiwrapper.net.ApiResource.RequestMethod;
import co.uiza.apiwrapper.net.ApiResource.RequestType;
import co.uiza.apiwrapper.net.util.ErrorMessage;
import co.uiza.apiwrapper.net.util.UizaError;
import co.uiza.apiwrapper.net.util.UizaResponse;

public class MainUizaResponseGetter implements UizaResponseGetter {

  private static final String DNS_CACHE_TTL_PROPERTY_NAME = "networkaddress.cache.ttl";

  private static final SSLSocketFactory socketFactory = new UizaSslSocketFactory();

  @Override
  public JsonElement request(RequestMethod method, String url, Map<String, Object> params,
      RequestType type) throws UizaException {
    Gson gsone = new Gson();
    JsonObject jsonParams = new JsonObject();
    if (params != null) {
      jsonParams = gsone.toJsonTree(params).getAsJsonObject();
    }

    return makeRequest(method, url, jsonParams, type);
  }

  private static JsonElement makeRequest(RequestMethod method, String url, JsonObject params,
      RequestType type) throws UizaException {
    String originalDnsCacheTtl = null;
    Boolean allowedToSetTtl = true;
    try {
      originalDnsCacheTtl = java.security.Security.getProperty(DNS_CACHE_TTL_PROPERTY_NAME);
      if (originalDnsCacheTtl != null) {
        java.security.Security.setProperty(DNS_CACHE_TTL_PROPERTY_NAME, "0");
      }
    } catch (SecurityException se) {
      allowedToSetTtl = false;
    }

    try {
      UizaResponse response = null;
      switch (type) {
        case NORMAL:
          response = getResponse(method, url, params);
          break;
        case MULTIPART:

          break;
        default:
          throw new RuntimeException(ErrorMessage.INVALID_REQUEST_TYPE);
      }

      int responseCode = response.code();
      if (responseCode < 200 || responseCode >= 300) {
        handleApiError(response.body(), responseCode, response.requestId());
      }

      JsonObject responseBody = null;
      JsonElement data = null;
      JsonParser parser = new JsonParser();
      try {
        responseBody = parser.parse(response.body()).getAsJsonObject();
        data = responseBody.get("data");
      } catch (JsonSyntaxException e) {
        raiseMalformedJsonError(response.body(), responseCode, response.requestId());
      }

      return data;
    } finally {
      if (allowedToSetTtl && originalDnsCacheTtl != null) {
        java.security.Security.setProperty(DNS_CACHE_TTL_PROPERTY_NAME, originalDnsCacheTtl);
      }
    }
  }

  private static UizaResponse getResponse(RequestMethod method, String url, JsonObject params)
      throws BadRequestException, UnprocessableException {
    String query = null;

    if (method == RequestMethod.GET) {
      try {
        query = createQuery(params);
      } catch (UnsupportedEncodingException e) {
        throw new UnprocessableException(ErrorMessage.ENCODE_FAILED, null, 422);
      }
    } else {
      query = params.toString();
    }

    return makeUrlConnectionRequest(method, url, query);
  }

  private static UizaResponse makeUrlConnectionRequest(RequestMethod method, String url,
      String query) throws BadRequestException {
    HttpURLConnection conn = null;
    try {
      switch (method) {
        case GET:
          conn = createGetConnection(url, query);
          break;
        case POST:
          conn = createPostConnection(url, query);
          break;
        case PUT:
          conn = createPutConnection(url, query);
          break;
        case DELETE:
          conn = createDeleteConnection(url, query);
          break;
        default:
          throw new BadRequestException(
              String.format("%s %s.", ErrorMessage.INVALID_REQUEST_METHOD, method), null, 400);
      }

      int responseCode = conn.getResponseCode();
      String responseBody;
      Map<String, List<String>> headers;
      if (responseCode >= 200 && responseCode < 300) {
        responseBody = getResponseBody(conn.getInputStream());
      } else {
        responseBody = getResponseBody(conn.getErrorStream());
      }
      headers = conn.getHeaderFields();

      return new UizaResponse(responseCode, responseBody, headers);
    } catch (IOException e) {
      throw new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, null, 400);
    } finally {
      if (conn != null) {
        conn.disconnect();
      }
    }
  }

  private static HttpURLConnection createGetConnection(String url, String query)
      throws IOException {
    String getUrl = formatUrl(url, query);
    HttpURLConnection conn = createUizaConnection(getUrl);
    conn.setRequestMethod("GET");

    return conn;
  }

  private static HttpURLConnection createPostConnection(String url, String query)
      throws IOException {
    HttpURLConnection conn = createUizaConnection(url);

    conn.setDoOutput(true);
    conn.setInstanceFollowRedirects(false);
    conn.setRequestMethod("POST");

    OutputStream output = conn.getOutputStream();
    output.write(query.getBytes(ApiResource.CHARSET));

    return conn;
  }

  private static HttpURLConnection createPutConnection(String url, String query)
      throws IOException {
    HttpURLConnection conn = createUizaConnection(url);

    conn.setDoOutput(true);
    conn.setRequestMethod("PUT");

    OutputStream output = conn.getOutputStream();
    output.write(query.getBytes(ApiResource.CHARSET));

    return conn;
  }

  private static HttpURLConnection createDeleteConnection(String url, String query)
      throws IOException {
    HttpURLConnection conn = createUizaConnection(url);

    conn.setDoOutput(true);
    conn.setRequestMethod("DELETE");

    OutputStream output = conn.getOutputStream();
    output.write(query.getBytes(ApiResource.CHARSET));

    return conn;
  }

  private static String formatUrl(String url, String query) {
    if (query == null || query.isEmpty()) {
      return url;
    } else {
      String separator = url.contains("?") ? "&" : "?";
      return String.format("%s%s%s", url, separator, query);
    }
  }

  private static HttpURLConnection createUizaConnection(String url) throws IOException {
    URL uizaUrl;
    uizaUrl = new URL(url);

    HttpURLConnection conn;
    if (Uiza.getConnectionProxy() != null) {
      conn = (HttpURLConnection) uizaUrl.openConnection(Uiza.getConnectionProxy());
      Authenticator.setDefault(new Authenticator() {

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
          return Uiza.getProxyCredential();
        }
      });
    } else {
      conn = (HttpURLConnection) uizaUrl.openConnection();
    }

    conn = (HttpURLConnection) uizaUrl.openConnection();
    conn.setConnectTimeout(Uiza.getConnectTimeout());
    conn.setReadTimeout(Uiza.getReadTimeout());
    conn.setUseCaches(false);

    for (Map.Entry<String, String> header : getHeaders().entrySet()) {
      conn.setRequestProperty(header.getKey(), header.getValue());
    }
    if (conn instanceof HttpsURLConnection) {
      ((HttpsURLConnection) conn).setSSLSocketFactory(socketFactory);
    }

    return conn;
  }

  static Map<String, String> getHeaders() {
    Map<String, String> headers = new HashMap<>();
    headers.put("Accept-Charset", ApiResource.CHARSET);
    headers.put("Authorization", Uiza.apiKey);
    headers.put("Uiza-Version", Uiza.apiVersion);
    headers.put("Content-Type", "application/json");

    return headers;
  }

  static String createQuery(JsonObject params) throws UnsupportedEncodingException {
    if (params == null || params.isJsonNull()) {
      return "";
    }

    StringBuilder queryStringBuffer = new StringBuilder();

    for (String key : params.keySet()) {
      if (queryStringBuffer.length() > 0) {
        queryStringBuffer.append("&");
      }

      queryStringBuffer.append(urlEncodePair(key, params.get(key).toString().replaceAll("\"", "")));
    }

    return queryStringBuffer.toString();
  }

  private static String urlEncodePair(String k, String v) throws UnsupportedEncodingException {
    return String.format("%s=%s", ApiResource.urlEncode(k), ApiResource.urlEncode(v));
  }

  private static String getResponseBody(InputStream responseStream) throws IOException {
    try (final Scanner scanner = new Scanner(responseStream, ApiResource.CHARSET)) {
      final String responseBody = scanner.useDelimiter("\\A").next();
      responseStream.close();

      return responseBody;
    }
  }

  private static void handleApiError(String responseBody, int responseCode, String requestId)
      throws UizaException, BadRequestException, ClientException, InternalServerException,
      NotFoundException, ServerException, ServiceUnavailableException, UnauthorizedException,
      UnprocessableException {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    try {
      gson.fromJson(responseBody, UizaError.class);
    } catch (JsonSyntaxException e) {
      raiseMalformedJsonError(responseBody, responseCode, requestId);
    }

    switch (responseCode) {
      case 400:
        throw new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, requestId, responseCode);
      case 401:
        throw new UnauthorizedException(ErrorMessage.UNAUTHORIZED_ERROR, requestId, responseCode);
      case 404:
        throw new NotFoundException(ErrorMessage.NOT_FOUND_ERROR, requestId, responseCode);
      case 422:
        throw new UnprocessableException(ErrorMessage.UNPROCESSABLE_ERROR, requestId, responseCode);
      case 500:
        throw new InternalServerException(ErrorMessage.INTERNAL_SERVER_ERROR, requestId,
            responseCode);
      case 503:
        throw new ServiceUnavailableException(ErrorMessage.SERVICE_UNAVAILABLE_ERROR, requestId,
            responseCode);
    }
    if (responseCode >= 400 && responseCode < 500) {
      throw new ClientException(ErrorMessage.SERVICE_UNAVAILABLE_ERROR, requestId, responseCode);
    }
    if (responseCode >= 500) {
      throw new ServerException(ErrorMessage.SERVER_ERROR, requestId, responseCode);
    }

    throw new UizaException(responseBody, requestId, responseCode);
  }

  private static void raiseMalformedJsonError(String responseBody, int responseCode,
      String requestId) throws JsonSyntaxException {
    throw new JsonSyntaxException(
        String.format(ErrorMessage.INVALID_RESPONSE, responseBody, responseCode));
  }
}
