package co.uiza.apiwrapper.model;

import java.util.Map;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import co.uiza.apiwrapper.exception.UizaException;
import co.uiza.apiwrapper.net.ApiResource;

public class Entity extends ApiResource {

  private static final String CLASS_DEFAULT_PATH = "media/entity";
  private static final String SEARCH_PATH = "search";
  private static final String STATUS_PUBLISH_PATH = "publish/status";
  private static final String AWS_UPLOAD_KEY_PATH = "admin/app/config/aws";

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
   * Get detail of an entity including all information of that entity
   *
   * @param entityParams a Map object storing key-value pairs of request parameter
   *
   */
  public static JsonObject retrieve(Map<String, Object> entityParams) throws UizaException {
    return request(RequestMethod.GET, buildRequestURL(CLASS_DEFAULT_PATH), entityParams);
  }

  /**
   * Search entity base on keyword entered
   *
   * @param entityParams a Map object storing key-value pairs of request parameter
   *
   */
  public static JsonObject search(Map<String, Object> entityParams) throws UizaException {
    String path_extension = String.format("%s/%s", CLASS_DEFAULT_PATH, SEARCH_PATH);

    return request(RequestMethod.GET, buildRequestURL(path_extension), entityParams);
  }

  /**
   * Get list of entities including all detail.
   *
   * @param entityParams a Map object storing key-value pairs of request parameter
   *
   */
  public static JsonObject list(Map<String, Object> entityParams) throws UizaException {
    return request(RequestMethod.GET, buildRequestURL(CLASS_DEFAULT_PATH), entityParams);
  }

  /**
   * Get entity status publish
   *
   * @param entityParams a Map object storing key-value pairs of request parameter
   *
   */
  public static JsonObject getStatusPublish(Map<String, Object> entityParams) throws UizaException {
    String path_extension = String.format("%s/%s", CLASS_DEFAULT_PATH, STATUS_PUBLISH_PATH);

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
  public static JsonObject getAWSKey() throws UizaException {
    return request(RequestMethod.GET, buildRequestURL(AWS_UPLOAD_KEY_PATH), null);
  }
}
