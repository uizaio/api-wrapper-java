package io.uiza.model;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.uiza.exception.BadRequestException;
import io.uiza.exception.UizaException;
import io.uiza.net.ApiResource;
import io.uiza.net.util.ErrorMessage;

/**
 * This API wrapper helps you adding storage (FTP, AWS S3) with UIZA. After synced, you can select
 * your content easier from your storage to create entity.
 */
public class Storage extends ApiResource {

  private static final String CLASS_DEFAULT_PATH = "media/storage";

  public enum DescriptionLink {
    ADD("http://dev-ap-southeast-1-api.uizadev.io/docs/#api-Media_Storage-create_storage"),

    RETRIEVE("http://dev-ap-southeast-1-api.uizadev.io/docs/#api-Media_Storage-list_storage"),

    UPDATE("http://dev-ap-southeast-1-api.uizadev.io/docs/#api-Media_Storage-update_storag"),

    REMOVE("http://dev-ap-southeast-1-api.uizadev.io/docs/#api-Media_Storage-delete_storage");

    private final String val;

    private DescriptionLink(String val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return val;
    }
  }

  public enum StorageType {
    FTP("ftp"),

    S3_COMPATIBLE("s3-compatible"),

    S3_UIZA("s3-uiza"),

    S3("s3");

    private final String val;

    private StorageType(String val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return val;
    }
  }

  /**
   * You can sync your storage (FTP, AWS S3) with UIZA. After sync, you can select your content
   * easier from your storage to create entity.
   *
   * @param storageParams a Map object storing key-value pairs of request parameter
   * @return created storage JSON object
   * @throws UizaException
   */
  public static JsonObject add(Map<String, Object> storageParams) throws UizaException {
    JsonElement response = request(RequestMethod.POST, buildRequestURL(CLASS_DEFAULT_PATH),
        storageParams, DescriptionLink.ADD.toString());
    String id = getId((JsonObject) checkResponseType(response));

    return retrieve(id);
  }

  /**
   * Get information of your added storage (FTP or AWS S3).
   *
   * @param id An id of a storage to retrieve
   * @return a storage JSON object with matched id
   * @throws UizaException
   */
  public static JsonObject retrieve(String id) throws UizaException {
    if (id == null || id.isEmpty()) {
      throw new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, "", 400);
    }

    Map<String, Object> storageParams = new HashMap<>();
    storageParams.put("id", id);

    JsonElement response = request(RequestMethod.GET, buildRequestURL(CLASS_DEFAULT_PATH),
        storageParams, DescriptionLink.RETRIEVE.toString());

    return checkResponseType(response);
  }

  /**
   * Update storage's information.
   *
   * @param id An id of a storage to update
   * @param storageParams a Map object storing key-value pairs of request parameter
   * @return updated storage JSON object
   * @throws UizaException
   */
  public static JsonObject update(String id, Map<String, Object> storageParams)
      throws UizaException {
    if (storageParams == null) {
      storageParams = new HashMap<>();
    }
    storageParams.put("id", id);
    request(RequestMethod.PUT, buildRequestURL(CLASS_DEFAULT_PATH), storageParams,
        DescriptionLink.UPDATE.toString());

    return retrieve(id);
  }

  /**
   * Remove storage that added to Uiza.
   *
   * @param id An id of a storage to remove
   * @return id of the deleted storage
   * @throws UizaException
   */
  public static JsonObject remove(String id) throws UizaException {
    Map<String, Object> storageParams = new HashMap<>();
    storageParams.put("id", id);
    JsonElement response = request(RequestMethod.DELETE, buildRequestURL(CLASS_DEFAULT_PATH),
        storageParams, DescriptionLink.REMOVE.toString());

    return checkResponseType(response);
  }
}
