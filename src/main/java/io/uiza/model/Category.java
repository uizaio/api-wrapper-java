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

public class Category extends ApiResource {

  private static final String CLASS_DEFAULT_PATH = "media/metadata";
  private static final String RELATION_PATH = "media/entity/related/metadata";

  public enum Type {

    @SerializedName("folder")
    FOLDER("folder"),

    @SerializedName("playlist")
    PLAYLIST("playlist"),

    @SerializedName("tag")
    TAG("tag");

    private final String val;

    private Type(String val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return val;
    }
  }

  /**
   * Create category for entity for easier management.
   * Category use to group all the same entities into a group (like folder, playlist, or tag)
   *
   * @param categoryParams a Map object storing key-value pairs of request parameter
   *
   */
  public static JsonObject create(Map<String, Object> categoryParams) throws UizaException {
    JsonElement response =
        request(RequestMethod.POST, buildRequestURL(CLASS_DEFAULT_PATH), categoryParams);
    String id = getId((JsonObject) checkResponseType(response));

    return retrieve(id);
  }

  /**
   * Get detail of category
   *
   * @param id An id of category to retrieve
   *
   */
  public static JsonObject retrieve(String id) throws UizaException {
    if (id == null || id.isEmpty()) {
      throw new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, "", 400);
    }

    Map<String, Object> categoryParams = new HashMap<>();
    categoryParams.put("id", id);
    JsonElement response =
        request(RequestMethod.GET, buildRequestURL(CLASS_DEFAULT_PATH), categoryParams);

    return checkResponseType(response);
  }

  /**
   * Get all categories including all details.
   */
  public static JsonArray list() throws UizaException {
    JsonElement response = request(RequestMethod.GET, buildRequestURL(CLASS_DEFAULT_PATH), null);

    return checkResponseType(response);
  }

  /**
   * Update information of category.
   *
   * @param id An id of category to update
   * @param categoryParams a Map object storing key-value pairs of request parameter
   *
   */
  public static JsonObject update(String id, Map<String, Object> categoryParams)
      throws UizaException {
    if (categoryParams == null) {
      categoryParams = new HashMap<>();
    }
    categoryParams.put("id", id);
    request(RequestMethod.PUT, buildRequestURL(CLASS_DEFAULT_PATH), categoryParams);

    return retrieve(id);
  }

  /**
   * Delete category
   *
   * @param id An id of category to delete
   *
   */
  public static JsonObject delete(String id) throws UizaException {
    Map<String, Object> categoryParams = new HashMap<>();
    categoryParams.put("id", id);
    JsonElement response =
        request(RequestMethod.DELETE, buildRequestURL(CLASS_DEFAULT_PATH), categoryParams);

    return checkResponseType(response);
  }

  /**
   * Add relation for entity and category
   *
   * @param categoryParams a Map object storing key-value pairs of request parameter
   */
  public static JsonArray createRelation(Map<String, Object> categoryParams) throws UizaException {
    JsonElement response =
        request(RequestMethod.POST, buildRequestURL(RELATION_PATH), categoryParams);

    return checkResponseType(response);
  }

  /**
   * Delete relation for entity and category
   *
   * @param categoryParams a Map object storing key-value pairs of request parameter
   */
  public static JsonArray deleteRelation(Map<String, Object> categoryParams) throws UizaException {
    JsonElement response =
        request(RequestMethod.DELETE, buildRequestURL(RELATION_PATH), categoryParams);

    return checkResponseType(response);
  }
}
