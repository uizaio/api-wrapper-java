package co.uiza.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import co.uiza.Uiza;
import co.uiza.exception.UizaException;

public abstract class ApiResource {

  public static final String CHARSET = "UTF-8";

  private static UizaResponseGetter uizaResponseGetter = new MainUizaResponseGetter();
  private static final String API_PUBLIC_V3_PATH = "api/public/v3";

  public enum RequestMethod {
    GET, POST, PUT, DELETE
  }

  public enum RequestType {
    NORMAL, MULTIPART
  }

  /**
   * Build request URL based on API operations
   *
   * @param path_extension The path extension depends on each API operation
   * @return the base url of each API operation request
   */
  public static String buildRequestURL(String path_extension) {
    return String.format("%s/%s/%s", Uiza.apiDomain, API_PUBLIC_V3_PATH, path_extension);
  }

  /**
   * URL-encodes a string.
   */
  public static String urlEncode(String str) throws UnsupportedEncodingException {
    if (str == null) {
      return null;
    } else {
      return URLEncoder.encode(str, CHARSET).replaceAll("%5B", "[").replaceAll("%5D", "]");
    }
  }

  /**
   * Create a request with a normal request type
   *
   * @param method The request method (GET, POST, PUT, DELETE)
   * @param url The base url of a request
   * @param params A Map object of parameters
   * @return response of the request
   */
  public static JsonElement request(RequestMethod method, String url, Map<String, Object> params)
      throws UizaException {
    return ApiResource.uizaResponseGetter.request(method, url, params, RequestType.NORMAL);
  }

  /**
   * Get response object with the correct class type
   *
   * @param response
   * @return response in correct type (JsonObject or JsonArray)
   */
  @SuppressWarnings("unchecked")
  public static <T> T checkResponseType(JsonElement response) {
    if (response != null) {
      if (response.isJsonObject()) {
        return (T) response.getAsJsonObject();
      }
      if (response.isJsonArray()) {
        return (T) response.getAsJsonArray();
      }
    }

    return (T) response;
  }

  /**
   * Get id of the corresponding response
   *
   * @param response A JsonObject of response
   * @return the formatted id number from response
   */
  public static String getId(JsonObject response) {
    if (response != null && response.get("id") != null) {
      return response.get("id").toString().replaceAll("\"", "");
    }

    return "";
  }
}
