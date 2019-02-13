package co.uiza.apiwrapper.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import com.google.gson.JsonObject;
import co.uiza.apiwrapper.Uiza;
import co.uiza.apiwrapper.exception.UizaException;

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
  protected static String buildRequestURL(String path_extension) {
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
   * @return response of the request in JSON object
   */
  public static JsonObject request(RequestMethod method, String url, Map<String, Object> params)
      throws UizaException {
    return ApiResource.uizaResponseGetter.request(method, url, params, RequestType.NORMAL);
  }
}
