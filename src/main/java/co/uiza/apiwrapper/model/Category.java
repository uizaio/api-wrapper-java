package co.uiza.apiwrapper.model;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import co.uiza.apiwrapper.exception.BadRequestException;
import co.uiza.apiwrapper.exception.UizaException;
import co.uiza.apiwrapper.net.ApiResource;
import co.uiza.apiwrapper.net.util.ErrorMessage;

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

    private final String type;

    public String getType() {
      return type;
    }

    private Type(String type) {
      this.type = type;
    }
  }

  /**
   * Create category for entity for easier management.
   * Category use to group all the same entities into a group (like folder, playlist, or tag)
   *
   * @param categoryParams a Map object storing key-value pairs of request parameter
   *
   */
  public static JsonObject createCategory(Map<String, Object> categoryParams) throws UizaException {
    JsonElement response =
        request(RequestMethod.POST, buildRequestURL(CLASS_DEFAULT_PATH), categoryParams);
    String id = getId((JsonObject) checkResponseType(response));

    return retrieveCategory(id);
  }

  /**
   * Get detail of category
   *
   * @param id An id of category to retrieve
   *
   */
  public static JsonObject retrieveCategory(String id) throws UizaException {
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
  public static JsonArray listCategory() throws UizaException {
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
  public static JsonObject updateCategory(String id, Map<String, Object> categoryParams)
      throws UizaException {
    if (categoryParams == null) {
      categoryParams = new HashMap<>();
    }
    categoryParams.put("id", id);
    request(RequestMethod.PUT, buildRequestURL(CLASS_DEFAULT_PATH), categoryParams);

    return retrieveCategory(id);
  }

  /**
   * Delete category
   *
   * @param id An id of category to delete
   *
   */
  public static JsonObject deleteCategory(String id) throws UizaException {
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
  public static JsonArray createRelationCategory(Map<String, Object> categoryParams)
      throws UizaException {
    JsonElement response =
        request(RequestMethod.POST, buildRequestURL(RELATION_PATH), categoryParams);

    return checkResponseType(response);
  }

  /**
   * Delete relation for entity and category
   *
   * @param categoryParams a Map object storing key-value pairs of request parameter
   */
  public static JsonArray deleteRelationCategory(Map<String, Object> categoryParams)
      throws UizaException {
    JsonElement response =
        request(RequestMethod.DELETE, buildRequestURL(RELATION_PATH), categoryParams);

    return checkResponseType(response);
  }
}
