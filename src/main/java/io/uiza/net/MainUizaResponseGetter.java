package io.uiza.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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

import io.uiza.Uiza;
import io.uiza.exception.BadRequestException;
import io.uiza.exception.ClientException;
import io.uiza.exception.InternalServerException;
import io.uiza.exception.NotFoundException;
import io.uiza.exception.ServerException;
import io.uiza.exception.ServiceUnavailableException;
import io.uiza.exception.UizaException;
import io.uiza.exception.UnauthorizedException;
import io.uiza.exception.UnprocessableException;
import io.uiza.net.ApiResource.RequestMethod;
import io.uiza.net.ApiResource.RequestType;
import io.uiza.net.util.ErrorMessage;
import io.uiza.net.util.UizaError;
import io.uiza.net.util.UizaResponse;

public class MainUizaResponseGetter implements UizaResponseGetter {

  private static final String DNS_CACHE_TTL_PROPERTY_NAME = "networkaddress.cache.ttl";

  private static final SSLSocketFactory socketFactory = new UizaSslSocketFactory();

  @Override
  public JsonElement request(RequestMethod method, String url, Map<String, Object> params,
      RequestType type, String descriptionLink) throws UizaException {
    Gson gsone = new Gson();
    JsonObject jsonParams = new JsonObject();
    if (params != null) {
      jsonParams = gsone.toJsonTree(params).getAsJsonObject();
    }

    return makeRequest(method, url, jsonParams, type, descriptionLink);
  }

  private static JsonElement makeRequest(RequestMethod method, String url, JsonObject params,
      RequestType type, String descriptionLink) throws UizaException {
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
          response = getResponse(method, url, params, descriptionLink);
          break;
        case MULTIPART:
          break;
        default:
          throw new NotFoundException(ErrorMessage.INVALID_REQUEST_TYPE, "0", 404, descriptionLink);
      }

      if (response == null) {
        throw new NotFoundException(
            String.format(ErrorMessage.INVALID_RESPONSE, response, 404, "0"), "0", 404,
            descriptionLink);
      }

      int responseCode = response.code();
      if (responseCode < 200 || responseCode >= 300) {
        handleApiError(response.body(), responseCode, response.requestId(), descriptionLink);
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

  private static UizaResponse getResponse(RequestMethod method, String url, JsonObject params,
      String descriptionLink) throws BadRequestException, UnprocessableException {
    String query = null;

    if (method == RequestMethod.GET) {
      try {
        query = createQuery(params);
      } catch (UnsupportedEncodingException e) {
        throw new UnprocessableException(ErrorMessage.ENCODE_FAILED, null, 422, descriptionLink);
      }
    } else {
      query = params.toString();
    }

    return makeUrlConnectionRequest(method, url, query, descriptionLink);
  }

  private static UizaResponse makeUrlConnectionRequest(RequestMethod method, String url,
      String query, String descriptionLink) throws BadRequestException {
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
              String.format("%s %s.", ErrorMessage.INVALID_REQUEST_METHOD, method), null, 400,
              descriptionLink);
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
      throw new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, null, 400, descriptionLink);
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
    output.write(query.getBytes(StandardCharsets.UTF_8));

    return conn;
  }

  private static HttpURLConnection createPutConnection(String url, String query)
      throws IOException {
    HttpURLConnection conn = createUizaConnection(url);

    conn.setDoOutput(true);
    conn.setRequestMethod("PUT");

    OutputStream output = conn.getOutputStream();
    output.write(query.getBytes(StandardCharsets.UTF_8));

    return conn;
  }

  private static HttpURLConnection createDeleteConnection(String url, String query)
      throws IOException {
    HttpURLConnection conn = createUizaConnection(url);

    conn.setDoOutput(true);
    conn.setRequestMethod("DELETE");

    OutputStream output = conn.getOutputStream();
    output.write(query.getBytes(StandardCharsets.UTF_8));

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
    headers.put("Accept-Charset", StandardCharsets.UTF_8.toString());
    headers.put("Authorization", Uiza.authorization);
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
    try (final Scanner scanner = new Scanner(responseStream, StandardCharsets.UTF_8.toString())) {
      final String responseBody = scanner.useDelimiter("\\A").next();
      responseStream.close();

      return responseBody;
    }
  }

  private static void handleApiError(String responseBody, int responseCode, String requestId,
      String descriptionLink) throws UizaException {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    UizaError error = null;
    try {
      error = gson.fromJson(responseBody, UizaError.class);
    } catch (JsonSyntaxException e) {
      raiseMalformedJsonError(responseBody, responseCode, requestId);
    }

    String errorMessage;
    switch (responseCode) {
      case 400:
        errorMessage = getErrorMessage(ErrorMessage.BAD_REQUEST_ERROR, error.getMessage());
        throw new BadRequestException(errorMessage, requestId, responseCode, descriptionLink);
      case 401:
        errorMessage = getErrorMessage(ErrorMessage.UNAUTHORIZED_ERROR, error.getMessage());
        throw new UnauthorizedException(errorMessage, requestId, responseCode, descriptionLink);
      case 404:
        errorMessage = getErrorMessage(ErrorMessage.NOT_FOUND_ERROR, error.getMessage());
        throw new NotFoundException(errorMessage, requestId, responseCode, descriptionLink);
      case 422:
        errorMessage = getErrorMessage(ErrorMessage.UNPROCESSABLE_ERROR, error.getMessage());
        throw new UnprocessableException(errorMessage, requestId, responseCode, descriptionLink);
      case 500:
        errorMessage = getErrorMessage(ErrorMessage.INTERNAL_SERVER_ERROR, error.getMessage());
        throw new InternalServerException(errorMessage, requestId, responseCode, descriptionLink);
      case 503:
        errorMessage = getErrorMessage(ErrorMessage.SERVICE_UNAVAILABLE_ERROR, error.getMessage());
        throw new ServiceUnavailableException(errorMessage, requestId, responseCode,
            descriptionLink);
      default:
    }
    if (responseCode >= 400 && responseCode < 500) {
      errorMessage = getErrorMessage(ErrorMessage.CLIENT_ERROR, error.getMessage());
      throw new ClientException(errorMessage, requestId, responseCode, descriptionLink);
    }
    if (responseCode >= 500) {
      errorMessage = getErrorMessage(ErrorMessage.SERVER_ERROR, error.getMessage());
      throw new ServerException(errorMessage, requestId, responseCode, descriptionLink);
    }

    throw new UizaException(error.getMessage(), requestId, responseCode, descriptionLink);
  }

  private static void raiseMalformedJsonError(String responseBody, int responseCode,
      String requestId) {
    throw new JsonSyntaxException(
        String.format(ErrorMessage.INVALID_RESPONSE, responseBody, responseCode, requestId));
  }

  private static String getErrorMessage(String defaultMessage, String actualMessage) {
    return actualMessage == null || actualMessage.isEmpty() ? defaultMessage : actualMessage;
  }
}
