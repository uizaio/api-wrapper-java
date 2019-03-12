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
 * This API wrapper allows you to take different actions on your media files (or so called Entity).
 */
public class Entity extends ApiResource {

  private static final String PATH_EXTENSION_FORMAT = "%s/%s";
  private static final String CLASS_DEFAULT_PATH = "media/entity";
  private static final String SEARCH_PATH = "search";
  private static final String PUBLISH_PATH = "publish";
  private static final String STATUS_PUBLISH_PATH = "publish/status";
  private static final String AWS_UPLOAD_KEY_PATH = "admin/app/config/aws";

  public enum InputType {
    @SerializedName("http")
    HTTP("http"),

    @SerializedName("ftp")
    FTP("ftp"),

    @SerializedName("s3-uiza-dvr")
    S3_UIZA_DVR("s3-uiza-dvr"),

    @SerializedName("s3-uiza")
    S3_UIZA("s3-uiza"),

    @SerializedName("s3")
    S3("s3");

    private final String val;

    private InputType(String val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return val;
    }
  }

  public enum Type {
    @SerializedName("vod")
    VOD("vod"),

    @SerializedName("aod")
    AOD("aod");

    private final String val;

    private Type(String val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return val;
    }
  }

  public enum ReadyToPublish {
    @SerializedName("on")
    ON("on"),

    @SerializedName("off")
    OFF("off");

    private final String val;

    private ReadyToPublish(String val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return val;
    }
  }

  public enum PublishStatus {
    @SerializedName("init")
    INIT("init"),

    @SerializedName("processing")
    PROCESSING("processing"),

    @SerializedName("queue")
    QUEUE("queue"),

    @SerializedName("not-ready")
    NOT_READY("not-ready"),

    @SerializedName("success")
    SUCCESS("success"),

    @SerializedName("failed")
    FAILED("failed");

    private final String val;

    private PublishStatus(String val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return val;
    }
  }

  /**
   * Create entity using full URL. Direct HTTP, FTP or AWS S3 link are acceptable.
   *
   * @param entityParams a Map object storing key-value pairs of request parameter
   * @return created entity JSON object
   * @throws UizaException
   */
  public static JsonObject create(Map<String, Object> entityParams) throws UizaException {
    JsonElement response =
        request(RequestMethod.POST, buildRequestURL(CLASS_DEFAULT_PATH), entityParams);
    String id = getId((JsonObject) checkResponseType(response));

    return retrieve(id);
  }

  /**
   * Get detail of an entity including all information of that entity.
   *
   * @param id An id of an entity to retrieve
   * @return an entity JSON object with matched id
   * @throws UizaException
   */
  public static JsonObject retrieve(String id) throws UizaException {
    if (id == null || id.isEmpty()) {
      throw new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, "", 400);
    }

    Map<String, Object> entityParams = new HashMap<>();
    entityParams.put("id", id);
    JsonElement response =
        request(RequestMethod.GET, buildRequestURL(CLASS_DEFAULT_PATH), entityParams);

    return checkResponseType(response);
  }

  /**
   * Get list of all entities including all details.
   *
   * @return a list of all entities
   * @throws UizaException
   */
  public static JsonArray list() throws UizaException {
    return list(null);
  }

  /**
   * Get list of entities including all details.
   *
   * @param entityParams a Map object storing key-value pairs of request parameter
   * @return a list of entities matching request conditions
   * @throws UizaException
   */
  public static JsonArray list(Map<String, Object> entityParams) throws UizaException {
    if (entityParams != null && entityParams.containsKey("id")) {
      throw new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, "", 400);
    }

    JsonElement response =
        request(RequestMethod.GET, buildRequestURL(CLASS_DEFAULT_PATH), entityParams);

    return checkResponseType(response);
  }

  /**
   * Update entity's information.
   *
   * @param id An id of an entity to update
   * @param entityParams a Map object storing key-value pairs of request parameter
   * @return updated entity JSON object
   * @throws UizaException
   */
  public static JsonObject update(String id, Map<String, Object> entityParams)
      throws UizaException {
    if (entityParams == null) {
      entityParams = new HashMap<>();
    }
    entityParams.put("id", id);
    request(RequestMethod.PUT, buildRequestURL(CLASS_DEFAULT_PATH), entityParams);

    return retrieve(id);
  }

  /**
   * Delete entity.
   *
   * @param id An id of an entity to delete
   * @return id of the deleted entity
   * @throws UizaException
   */
  public static JsonObject delete(String id) throws UizaException {
    Map<String, Object> entityParams = new HashMap<>();
    entityParams.put("id", id);
    JsonElement response =
        request(RequestMethod.DELETE, buildRequestURL(CLASS_DEFAULT_PATH), entityParams);

    return checkResponseType(response);
  }

  /**
   * Search entity base on keyword entered.
   *
   * @param keyword A keyword for searching entities
   * @return entities matching the keyword
   * @throws UizaException
   */
  public static JsonArray search(String keyword) throws UizaException {
    String pathExtension = String.format(PATH_EXTENSION_FORMAT, CLASS_DEFAULT_PATH, SEARCH_PATH);
    Map<String, Object> entityParams = new HashMap<>();
    entityParams.put("keyword", keyword);
    JsonElement response = request(RequestMethod.GET, buildRequestURL(pathExtension), entityParams);

    return checkResponseType(response);
  }

  /**
   * Publish entity to CDN, use for streaming.
   *
   * @param id An id of an entity to publish to CDN
   * @return publish status of that entity
   * @throws UizaException
   */
  public static JsonObject publish(String id) throws UizaException {
    String pathExtension = String.format(PATH_EXTENSION_FORMAT, CLASS_DEFAULT_PATH, PUBLISH_PATH);
    Map<String, Object> entityParams = new HashMap<>();
    entityParams.put("id", id);
    JsonElement response =
        request(RequestMethod.POST, buildRequestURL(pathExtension), entityParams);

    return checkResponseType(response);
  }

  /**
   * Get entity status publish.
   *
   * @param id An id of entity to get publish status
   * @return publish status of that entity
   * @throws UizaException
   */
  public static JsonObject getStatusPublish(String id) throws UizaException {
    String pathExtension =
        String.format(PATH_EXTENSION_FORMAT, CLASS_DEFAULT_PATH, STATUS_PUBLISH_PATH);
    Map<String, Object> entityParams = new HashMap<>();
    entityParams.put("id", id);
    JsonElement response = request(RequestMethod.GET, buildRequestURL(pathExtension), entityParams);

    return checkResponseType(response);
  }

  /**
   *
   * Get AWS upload key.
   *
   * @return the bucket temporary upload storage and key for upload, so that you can push your file
   *         to Uiza’s storage and get the link for URL upload and create entity
   * @throws UizaException
   */
  public static JsonObject getAwsUploadKey() throws UizaException {
    JsonElement response = request(RequestMethod.GET, buildRequestURL(AWS_UPLOAD_KEY_PATH), null);

    return checkResponseType(response);
  }
}
