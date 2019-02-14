package co.uiza.apiwrapper.model;

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
   * @param entityParams a JSON object storing key-value pairs of request parameter
   *
   */
  public static JsonObject create(JsonObject entityParams) throws UizaException {
    return request(RequestMethod.POST, buildRequestURL(CLASS_DEFAULT_PATH), entityParams);
  }

  /**
   * Get detail of an entity including all information of that entity
   *
   * @param entityParams a JSON object storing key-value pairs of request parameter
   *
   */
  public static JsonObject retrieve(JsonObject entityParams) throws UizaException {
    return request(RequestMethod.GET, buildRequestURL(CLASS_DEFAULT_PATH), entityParams);
  }

  /**
  * Search entity base on keyword entered
  *
  * @param entityParams a JSON object storing key-value pairs of request parameter
  *
  */
  public static JsonObject search(JsonObject entityParams) throws UizaException {
    String path_extension = String.format("%s/%s", CLASS_DEFAULT_PATH, SEARCH_PATH);

    return request(RequestMethod.GET, buildRequestURL(path_extension), entityParams);
  }

  /**
  * Get list of entities including all detail.
  *
  * @param entityParams a JSON object storing key-value pairs of request parameter
  *
  */
  public static JsonObject list(JsonObject entityParams) throws UizaException {
    return request(RequestMethod.GET, buildRequestURL(CLASS_DEFAULT_PATH), entityParams);
  }

  /**
  * Publish entity to CDN, use for streaming
  *
  * @param entityParams A JSON object storing key-value pairs of request parameter
  */
  public static JsonObject publishToCDN(JsonObject entityParams) throws UizaException {
    String path_extension = String.format("%s/%s", CLASS_DEFAULT_PATH, PUBLISH_PATH);

    return request(RequestMethod.POST, buildRequestURL(path_extension), entityParams);
  }

  /**
  * Get entity status publish
  *
  * @param entityParams a JSON object storing key-value pairs of request parameter
  *
  */
  public static JsonObject getStatusPublish(JsonObject entityParams) throws UizaException {
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
