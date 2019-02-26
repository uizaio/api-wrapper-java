package io.uiza.model;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.uiza.exception.BadRequestException;
import io.uiza.exception.UizaException;
import io.uiza.net.ApiResource;
import io.uiza.net.util.ErrorMessage;

public class Callback extends ApiResource {

  private static final String CLASS_DEFAULT_PATH = "media/entity/callback";

  public enum Method {
    GET, POST, PUT;
  }

  /**
   * Setup a callback to your server when an entity is completed for upload or public.
   *
   * @param callbackParams a Map object storing key-value pairs of request parameter
   */
  public static JsonObject create(Map<String, Object> callbackParams) throws UizaException {
    JsonElement response =
        request(RequestMethod.POST, buildRequestURL(CLASS_DEFAULT_PATH), callbackParams);
    String id = getId((JsonObject) checkResponseType(response));

    return retrieve(id);
  }

  /**
   * Retrieves the details of an existing callback.
   *
   * @param id An id of callback to retrieve
   *
   */
  public static JsonObject retrieve(String id) throws UizaException {
    if (id == null || id.isEmpty()) {
      throw new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, "", 400);
    }

    Map<String, Object> callbackParams = new HashMap<>();
    callbackParams.put("id", id);

    JsonElement response =
        request(RequestMethod.GET, buildRequestURL(CLASS_DEFAULT_PATH), callbackParams);

    return checkResponseType(response);
  }

  /**
   * Setup a callback to your server when an entity is completed for upload or public.
   *
   * @param id An id of callback to update
   * @param callbackParams a Map object storing key-value pairs of request parameter
   *
   */
  public static JsonObject update(String id, Map<String, Object> callbackParams)
      throws UizaException {
    if (callbackParams == null) {
      callbackParams = new HashMap<>();
    }
    callbackParams.put("id", id);
    request(RequestMethod.PUT, buildRequestURL(CLASS_DEFAULT_PATH), callbackParams);

    return retrieve(id);
  }

  /**
   * Delete an existing callback.
   *
   * @param id An id of callback to delete
   *
   */
  public static JsonObject delete(String id) throws UizaException {
    Map<String, Object> callbackParams = new HashMap<>();
    callbackParams.put("id", id);
    JsonElement response =
        request(RequestMethod.DELETE, buildRequestURL(CLASS_DEFAULT_PATH), callbackParams);

    return checkResponseType(response);
  }
}
