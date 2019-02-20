package co.uiza.model;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import co.uiza.exception.BadRequestException;
import co.uiza.exception.UizaException;
import co.uiza.net.ApiResource;
import co.uiza.net.util.ErrorMessage;

public class Storage extends ApiResource {

  private static final String CLASS_DEFAULT_PATH = "media/storage";

  public enum StorageType {
    @SerializedName("ftp")
    FTP("ftp"),

    @SerializedName("s3")
    S3("s3");

    private final String storageType;

    public String getStorageType() {
      return storageType;
    }

    private StorageType(String storageType) {
      this.storageType = storageType;
    }
  }

  /**
   * You can sync your storage (FTP, AWS S3) with UIZA.
   * After sync, you can select your content easier from your storage to create entity.
   *
   * @param storageParams a Map object storing key-value pairs of request parameter
   */
  public static JsonObject add(Map<String, Object> storageParams) throws UizaException {
    JsonElement response =
        request(RequestMethod.POST, buildRequestURL(CLASS_DEFAULT_PATH), storageParams);
    String id = getId((JsonObject) checkResponseType(response));

    return retrieve(id);
  }

  /**
   * Get information of your added storage (FTP or AWS S3)
   *
   * @param id An id of storage to retrieve
   *
   */
  public static JsonObject retrieve(String id) throws UizaException {
    if (id == null || id.isEmpty()) {
      throw new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, "", 400);
    }

    Map<String, Object> storageParams = new HashMap<>();
    storageParams.put("id", id);

    JsonElement response =
        request(RequestMethod.GET, buildRequestURL(CLASS_DEFAULT_PATH), storageParams);

    return checkResponseType(response);
  }

  /**
   * Update storage's information.
   *
   * @param id An id of storage to update
   * @param storageParams a Map object storing key-value pairs of request parameter
   *
   */
  public static JsonObject update(String id, Map<String, Object> storageParams)
      throws UizaException {
    if (storageParams == null) {
      storageParams = new HashMap<>();
    }
    storageParams.put("id", id);
    request(RequestMethod.PUT, buildRequestURL(CLASS_DEFAULT_PATH), storageParams);

    return retrieve(id);
  }

  /**
   * Remove storage that added to Uiza
   *
   * @param id An id of storage to remove
   *
   */
  public static JsonObject remove(String id) throws UizaException {
    Map<String, Object> storageParams = new HashMap<>();
    storageParams.put("id", id);
    JsonElement response =
        request(RequestMethod.DELETE, buildRequestURL(CLASS_DEFAULT_PATH), storageParams);

    return checkResponseType(response);
  }
}
