package co.uiza.apiwrapper.model;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import co.uiza.apiwrapper.exception.BadRequestException;
import co.uiza.apiwrapper.exception.UizaException;
import co.uiza.apiwrapper.net.ApiResource;
import co.uiza.apiwrapper.net.util.ErrorMessage;

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
   * Create a FTP storage
   *
   * @param storageParams
   */
  public static JsonObject createFtpStorage(Map<String, Object> storageParams)
      throws UizaException {
    if (storageParams != null) {
      storageParams.put("storageType", StorageType.FTP.getStorageType());
    }

    JsonElement response =
        request(RequestMethod.POST, buildRequestURL(CLASS_DEFAULT_PATH), storageParams);
    String id = getId((JsonObject) checkResponseType(response));

    return retrieveStorage(id);
  }

  /**
   * You can sync your storage (FTP, AWS S3) with UIZA.
   * After sync, you can select your content easier from your storage to create entity.
   * Create a AWS S3 storage
   *
   * @param storageParams
   */
  public static JsonObject createAwsS3Storage(Map<String, Object> storageParams)
      throws UizaException {
    if (storageParams != null) {
      storageParams.put("storageType", StorageType.S3.getStorageType());
    }

    JsonElement response =
        request(RequestMethod.POST, buildRequestURL(CLASS_DEFAULT_PATH), storageParams);
    String id = getId((JsonObject) checkResponseType(response));

    return retrieveStorage(id);
  }

  /**
   * Get information of your added storage (FTP or AWS S3)
   *
   * @param id An id of storage to retrieve
   *
   */
  public static JsonObject retrieveStorage(String id) throws UizaException {
    if (id == null || id.isEmpty()) {
      throw new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, "", 400);
    }

    Map<String, Object> storageParams = new HashMap<>();
    storageParams.put("id", id);

    JsonElement response =
        request(RequestMethod.GET, buildRequestURL(CLASS_DEFAULT_PATH), storageParams);

    return checkResponseType(response);
  }
}
