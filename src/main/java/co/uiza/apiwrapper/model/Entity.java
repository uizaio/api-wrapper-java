package co.uiza.apiwrapper.model;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import co.uiza.apiwrapper.exception.UizaException;
import co.uiza.apiwrapper.net.ApiResource;

public class Entity extends ApiResource {

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

    @SerializedName("s3-uiza")
    S3_UIZA("s3-uiza"),

    @SerializedName("s3")
    S3("s3");

    private final String inputType;

    public String getInputType() {
      return inputType;
    }

    private InputType(String inputType) {
      this.inputType = inputType;
    }
  }

  public enum PublishStatus {
    @SerializedName("queue")
    QUEUE("queue"),

    @SerializedName("not-ready")
    NOT_READY("not-ready"),

    @SerializedName("success")
    SUCCESS("success"),

    @SerializedName("failed")
    FAILED("failed");

    private final String status;

    public String getStatus() {
      return status;
    }

    private PublishStatus(String status) {
      this.status = status;
    }
  }

  /**
   * Create entity using full URL. Direct HTTP, FTP or AWS S3 link are acceptable.
   *
   * @param entityParams a Map object storing key-value pairs of request parameter
   *
   */
  public static JsonObject createEntity(Map<String, Object> entityParams) throws UizaException {
    return request(RequestMethod.POST, buildRequestURL(CLASS_DEFAULT_PATH), entityParams);
  }

  /**
   * Get detail of an entity including all information of that entity
   *
   * @param id An id of entity to retrieve
   *
   */
  public static JsonObject retrieveEntity(String id) throws UizaException {
    Map<String, Object> entityParams = new HashMap<>();
    entityParams.put("id", id);

    return request(RequestMethod.GET, buildRequestURL(CLASS_DEFAULT_PATH), entityParams);
  }

  /**
  * Get list of entities including all detail.
  *
  * @param entityParams a Map object storing key-value pairs of request parameter
  *
  */
  public static JsonObject listEntity(Map<String, Object> entityParams) throws UizaException {
    return request(RequestMethod.GET, buildRequestURL(CLASS_DEFAULT_PATH), entityParams);
  }

  /**
   * Update entity's information.
   *
   * @param id An id of entity to update
   * @param entityParams a Map object storing key-value pairs of request parameter
   *
   */
  public static JsonObject updateEntity(String id, Map<String, Object> entityParams)
      throws UizaException {
    entityParams.put("id", id);

    return request(RequestMethod.PUT, buildRequestURL(CLASS_DEFAULT_PATH), entityParams);
  }

  /**
   * Delete entity
   *
   * @param id An id of entity to delete
   *
   */
  public static JsonObject deleteEntity(String id) throws UizaException {
    Map<String, Object> entityParams = new HashMap<>();
    entityParams.put("id", id);

    return request(RequestMethod.DELETE, buildRequestURL(CLASS_DEFAULT_PATH), entityParams);
  }

  /**
  * Search entity base on keyword entered
  *
  * @param keyword A keyword for searching entities
  *
  */
  public static JsonObject searchEntity(String keyword) throws UizaException {
    String path_extension = String.format("%s/%s", CLASS_DEFAULT_PATH, SEARCH_PATH);
    Map<String, Object> entityParams = new HashMap<>();
    entityParams.put("keyword", keyword);

    return request(RequestMethod.GET, buildRequestURL(path_extension), entityParams);
  }

  /**
  * Publish entity to CDN, use for streaming
  *
  * @param id An id of entity to publish to CDN
  */
  public static JsonObject publishEntity(String id) throws UizaException {
    String path_extension = String.format("%s/%s", CLASS_DEFAULT_PATH, PUBLISH_PATH);
    Map<String, Object> entityParams = new HashMap<>();
    entityParams.put("id", id);

    return request(RequestMethod.POST, buildRequestURL(path_extension), entityParams);
  }

  /**
  * Get entity status publish
  *
  * @param id An id of entity to get publish status
  *
  */
  public static JsonObject getStatusPublishEntity(String id) throws UizaException {
    String path_extension = String.format("%s/%s", CLASS_DEFAULT_PATH, STATUS_PUBLISH_PATH);
    Map<String, Object> entityParams = new HashMap<>();
    entityParams.put("id", id);

    return request(RequestMethod.GET, buildRequestURL(path_extension), entityParams);
  }

  /**
   *
   * Get AWS upload key
   *
   * Return the bucket temporary upload storage & key for upload, so that you can push your file to
   * Uiza’s storage and get the link for URL upload & create entity
   *
   */
  public static JsonObject getAwsKeyEntity() throws UizaException {
    return request(RequestMethod.GET, buildRequestURL(AWS_UPLOAD_KEY_PATH), null);
  }
}
