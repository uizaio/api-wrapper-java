package io.uiza.model;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.uiza.exception.BadRequestException;
import io.uiza.exception.UizaException;
import io.uiza.net.ApiResource;
import io.uiza.net.util.ErrorMessage;

public class Live extends ApiResource {
  private static final String CLASS_DEFAULT_PATH = "live/entity";
  private static final String LIVE_VIEW_PATH = "tracking/current-view";
  private static final String RECORD_PATH = "dvr";

  /**
   * Retrieves the details of an existing event.
   *
   * @param id An id of live event to retrieve
   *
   */
  public static JsonObject retrieve(String id) throws UizaException {
    if (id == null || id.isEmpty()) {
      throw new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, "", 400);
    }

    Map<String, Object> liveParams = new HashMap<>();
    liveParams.put("id", id);
    JsonElement response =
        request(RequestMethod.GET, buildRequestURL(CLASS_DEFAULT_PATH), liveParams);

    return checkResponseType(response);
  }

  /**
   * Get a live view status.
   * This view only show when event has been started and being processing.
   *
   * @param id An id of live event to get view
   *
   */
  public static JsonObject getView(String id) throws UizaException {
    String path_extension = String.format("%s/%s", CLASS_DEFAULT_PATH, LIVE_VIEW_PATH);
    Map<String, Object> liveParams = new HashMap<>();
    liveParams.put("id", id);
    JsonElement response = request(RequestMethod.GET, buildRequestURL(path_extension), liveParams);

    return checkResponseType(response);
  }

  /**
   * Retrieves list of recorded file after streamed
   * (only available when your live event has turned on Record feature)
   *
   */
  public static JsonArray listRecorded() throws UizaException {
    String path_extension = String.format("%s/%s", CLASS_DEFAULT_PATH, RECORD_PATH);
    JsonElement response = request(RequestMethod.GET, buildRequestURL(path_extension), null);

    return checkResponseType(response);
  }
}
