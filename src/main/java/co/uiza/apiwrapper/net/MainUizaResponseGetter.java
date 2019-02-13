package co.uiza.apiwrapper.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import co.uiza.apiwrapper.Uiza;
import co.uiza.apiwrapper.exception.ApiConnectionException;
import co.uiza.apiwrapper.exception.ApiException;
import co.uiza.apiwrapper.exception.AuthenticationException;
import co.uiza.apiwrapper.exception.InvalidRequestException;
import co.uiza.apiwrapper.exception.ResourceNotFoundException;
import co.uiza.apiwrapper.exception.UizaException;
import co.uiza.apiwrapper.net.ApiResource.RequestMethod;
import co.uiza.apiwrapper.net.ApiResource.RequestType;
import co.uiza.apiwrapper.net.util.Parameter;
import co.uiza.apiwrapper.net.util.ParamsFlattener;
import co.uiza.apiwrapper.net.util.UizaError;
import co.uiza.apiwrapper.net.util.UizaResponse;

public class MainUizaResponseGetter implements UizaResponseGetter {

  private static final String DNS_CACHE_TTL_PROPERTY_NAME = "networkaddress.cache.ttl";

  private static final SSLSocketFactory socketFactory = new UizaSslSocketFactory();

  @Override
  public JsonObject request(RequestMethod method, String url, Map<String, Object> params,
      RequestType type) throws UizaException {

    return makeRequest(method, url, params, type);
  }

  private static JsonObject makeRequest(RequestMethod method, String url,
      Map<String, Object> params, RequestType type) throws UizaException {
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
          throw new RuntimeException("Invalid ApiResource request type.");
      }

      int responseCode = response.code();
      if (responseCode < 200 || responseCode >= 300) {
        handleApiError(response.body(), responseCode, response.requestId());
      }

      JsonObject responseBody = null;
      JsonParser parser = new JsonParser();
      try {
        responseBody = parser.parse(response.body()).getAsJsonObject();
      } catch (JsonSyntaxException e) {
        raiseMalformedJsonError(response.body(), responseCode, response.requestId());
      }

      return responseBody;
    } finally {
      if (allowedToSetTtl && originalDnsCacheTtl != null) {
        java.security.Security.setProperty(DNS_CACHE_TTL_PROPERTY_NAME, originalDnsCacheTtl);
      }
    }
  }

  private static UizaResponse getResponse(RequestMethod method, String url,
      Map<String, Object> params) throws InvalidRequestException, ApiConnectionException {
    String query;
    try {
      query = createQuery(params);
    } catch (UnsupportedEncodingException e) {
      throw new InvalidRequestException("Unable to encode parameters to " + ApiResource.CHARSET,
          null, null, 0, e);
    }

    try {
      return makeUrlConnectionRequest(method, url, query);
    } catch (Exception e) {
      throw e;
    }
  }

  private static UizaResponse makeUrlConnectionRequest(RequestMethod method, String url,
      String query) throws ApiConnectionException {
    HttpURLConnection conn = null;
    try {
      switch (method) {
        case GET:
          conn = createGetConnection(url, query);
          break;
        case POST:

          break;
        case PUT:

          break;
        case DELETE:

          break;
        default:
          throw new ApiConnectionException(String.format("Unrecognized HTTP method %s.", method));
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
      throw new ApiConnectionException(String.format(
          "IOException during API request to Uiza (%s): %s", Uiza.apiDomain, e.getMessage()), e);
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
    conn.setRequestProperty("Content-Type", "application/json");
    conn.setRequestMethod("GET");

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

    return headers;
  }

  static String createQuery(Map<String, Object> params)
      throws UnsupportedEncodingException, InvalidRequestException {
    StringBuilder queryStringBuffer = new StringBuilder();
    List<Parameter> flatParams = ParamsFlattener.flattenParams(params);
    Iterator<Parameter> it = flatParams.iterator();

    while (it.hasNext()) {
      if (queryStringBuffer.length() > 0) {
        queryStringBuffer.append("&");
      }
      Parameter param = it.next();
      queryStringBuffer.append(urlEncodePair(param.key, param.value));
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
      throws ApiException, AuthenticationException, InvalidRequestException,
      ResourceNotFoundException {
    UizaError error = null;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    try {
      error = gson.fromJson(responseBody, UizaError.class);
    } catch (JsonSyntaxException e) {
      raiseMalformedJsonError(responseBody, responseCode, requestId);
    }

    ArrayList<?> dataList = null;
    if (error.getData() instanceof ArrayList) {
      dataList = (ArrayList<?>) error.getData();
    }

    switch (responseCode) {
      case 400:
        throw new InvalidRequestException(
            String.format("%s. (%s)", error.getMessage(), dataList.get(0).toString()), null,
            requestId, responseCode, null);
      case 401:
        throw new AuthenticationException(
            "No API key provided. (HINT: set your API key using 'Uiza.apiKey = <API-KEY>'.",
            requestId, responseCode);
      case 404:
        throw new ResourceNotFoundException("The requested resource does not exist.", requestId,
            responseCode);
      case 422:
        throw new InvalidRequestException(String.format("%s. (%s)",
            "The syntax of the request is incorrect (often cause of wrong parameter)",
            dataList.get(0).toString()), null, requestId, responseCode, null);
      case 500:
      case 503:
      default:
        throw new ApiException(error.getMessage(), requestId, responseCode, null);
    }
  }

  private static void raiseMalformedJsonError(String responseBody, int responseCode,
      String requestId) throws ApiException {
    throw new ApiException(
        String.format("Invalid response object from API: %s. (HTTP response code was %d)",
            responseBody, responseCode),
        requestId, responseCode, null);
  }
}
