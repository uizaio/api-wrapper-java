package io.uiza.model;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import io.uiza.exception.BadRequestException;
import io.uiza.exception.UizaException;
import io.uiza.net.ApiResource;
import io.uiza.net.util.ErrorMessage;

/**
 * This API wrapper allows you to create and manage live streaming event.
 */
public class Live extends ApiResource {

  private static final String PATH_EXTENSION_FORMAT = "%s/%s";
  private static final String CLASS_DEFAULT_PATH = "live/entity";
  private static final String FEED_PATH = "feed";
  private static final String LIVE_VIEW_PATH = "tracking/current-view";
  private static final String RECORD_PATH = "dvr";
  private static final String VOD_PATH = "dvr/convert-to-vod";

  public enum Mode {
    @SerializedName("pull")
    PULL("pull"),

    @SerializedName("push")
    PUSH("push");

    private final String val;

    private Mode(String val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return val;
    }
  }

  public enum Status {
    @SerializedName("init")
    INIT("init"),

    @SerializedName("start")
    START("start"),

    @SerializedName("stop")
    STOP("stop"),

    @SerializedName("error")
    ERROR("error"),

    @SerializedName("in-process")
    IN_PROCESS("in-process"),

    @SerializedName("not-streaming")
    NOT_STREAMING("not-streaming");

    private final String val;

    private Status(String val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return val;
    }
  }

  public enum Encode {
    @SerializedName("0")
    NO_ENCODE(0),

    @SerializedName("1")
    ENCODE(1);

    private final int val;

    private Encode(int val) {
      this.val = val;
    }

    public int getVal() {
      return val;
    }
  }

  public enum Dvr {
    @SerializedName("0")
    NO_RECORD(0),

    @SerializedName("1")
    ACTIVE_RECORD(1);

    private final int val;

    private Dvr(int val) {
      this.val = val;
    }

    public int getVal() {
      return val;
    }
  }

  public enum Drm {
    @SerializedName("0")
    NO_RECORD(0),

    @SerializedName("1")
    ACTIVE_RECORD(1);

    private final int val;

    private Drm(int val) {
      this.val = val;
    }

    public int getVal() {
      return val;
    }
  }

  public enum ResourceMode {
    @SerializedName("single")
    SINGLE("single"),

    @SerializedName("redundant")
    REDUNDANT("redundant");

    private final String val;

    private ResourceMode(String val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return val;
    }
  }

  /**
   * Create a live streaming and manage the live streaming input (output). A live stream can be set
   * up and start later or start right after set up. Live Channel Minutes counts when the event
   * starts.
   *
   * @param liveParams a Map object storing key-value pairs of request parameter
   * @return created live event JSON object
   * @throws UizaException
   */
  public static JsonObject create(Map<String, Object> liveParams) throws UizaException {
    JsonElement response =
        request(RequestMethod.POST, buildRequestURL(CLASS_DEFAULT_PATH), liveParams);
    String id = getId((JsonObject) checkResponseType(response));

    return retrieve(id);
  }

  /**
   * Retrieves the details of an existing event.
   *
   * @param id An id of a live event to retrieve
   * @return a live event JSON object with matched id
   * @throws UizaException
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
   * Update the specific live event by edit values of parameters.
   *
   * @param id An id of a live event to update
   * @param liveParams a Map object storing key-value pairs of request parameter
   * @return updated live event JSON object
   * @throws UizaException
   */
  public static JsonObject update(String id, Map<String, Object> liveParams) throws UizaException {
    if (liveParams == null) {
      liveParams = new HashMap<>();
    }
    liveParams.put("id", id);
    request(RequestMethod.PUT, buildRequestURL(CLASS_DEFAULT_PATH), liveParams);

    return retrieve(id);
  }

  /**
   * Start a live event that has been create success. The Live channel minute start count whenever
   * the event start success.
   *
   * @param id An id of a live event to start feeding
   * @return information of the started live event
   * @throws UizaException
   */
  public static JsonObject startFeed(String id) throws UizaException {
    String pathExtension = String.format(PATH_EXTENSION_FORMAT, CLASS_DEFAULT_PATH, FEED_PATH);
    Map<String, Object> liveParams = new HashMap<>();
    liveParams.put("id", id);
    JsonElement response = request(RequestMethod.POST, buildRequestURL(pathExtension), liveParams);

    return checkResponseType(response);
  }

  /**
   * Get a live view status. This view only show when event has been started and being processing.
   *
   * @param id An id of a live event to get view
   * @return the view status of a live event
   * @throws UizaException
   */
  public static JsonObject getView(String id) throws UizaException {
    String pathExtension = String.format(PATH_EXTENSION_FORMAT, CLASS_DEFAULT_PATH, LIVE_VIEW_PATH);
    Map<String, Object> liveParams = new HashMap<>();
    liveParams.put("id", id);
    JsonElement response = request(RequestMethod.GET, buildRequestURL(pathExtension), liveParams);

    return checkResponseType(response);
  }

  /**
   * Stop a live event.
   *
   * @param id An id of a live event to stop
   * @return information of the stopped live event
   * @throws UizaException
   */
  public static JsonObject stopFeed(String id) throws UizaException {
    String pathExtension = String.format(PATH_EXTENSION_FORMAT, CLASS_DEFAULT_PATH, FEED_PATH);
    Map<String, Object> liveParams = new HashMap<>();
    liveParams.put("id", id);
    JsonElement response = request(RequestMethod.PUT, buildRequestURL(pathExtension), liveParams);

    return checkResponseType(response);
  }

  /**
   * Retrieves list of recorded file after streamed (only available when your live event has turned
   * on Record feature)
   *
   * @return a list of recorded file after streamed
   * @throws UizaException
   */
  public static JsonArray listRecorded() throws UizaException {
    String pathExtension = String.format(PATH_EXTENSION_FORMAT, CLASS_DEFAULT_PATH, RECORD_PATH);
    JsonElement response = request(RequestMethod.GET, buildRequestURL(pathExtension), null);

    return checkResponseType(response);
  }

  /**
   * Delete a recorded file
   *
   * @param id An id of a record (get from record list) to delete
   * @return id of the deleted record
   * @throws UizaException
   */
  public static JsonObject delete(String id) throws UizaException {
    String pathExtension = String.format(PATH_EXTENSION_FORMAT, CLASS_DEFAULT_PATH, RECORD_PATH);
    Map<String, Object> liveParams = new HashMap<>();
    liveParams.put("id", id);
    JsonElement response =
        request(RequestMethod.DELETE, buildRequestURL(pathExtension), liveParams);

    return checkResponseType(response);
  }

  /**
   * Convert recorded file into VOD entity. After converted, your file can be stream via Uiza's CDN.
   *
   * @param id An id of a live event to convert into VOD
   * @return id of the converted recored file
   * @throws UizaException
   */
  public static JsonObject convertToVod(String id) throws UizaException {
    String pathExtension = String.format(PATH_EXTENSION_FORMAT, CLASS_DEFAULT_PATH, VOD_PATH);
    Map<String, Object> liveParams = new HashMap<>();
    liveParams.put("id", id);
    JsonElement response = request(RequestMethod.POST, buildRequestURL(pathExtension), liveParams);

    return checkResponseType(response);
  }
}
