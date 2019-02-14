package co.uiza.apiwrapper.net;

import java.util.Map;
import com.google.gson.JsonObject;
import co.uiza.apiwrapper.exception.UizaException;
import co.uiza.apiwrapper.net.ApiResource.RequestMethod;
import co.uiza.apiwrapper.net.ApiResource.RequestType;

public interface UizaResponseGetter {

  /**
   * @param method The request method (GET, POST, PUT, DELETE)
   * @param url The base url of a request
   * @param params The Map object of request parameters
   * @param type The type of a request (NORMAL, MULTIPART)
   * @return response of an API request as JSON object
   */
  JsonObject request(RequestMethod method, String url, Map<String, Object> params, RequestType type)
      throws UizaException;
}
