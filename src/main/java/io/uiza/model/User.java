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

public class User extends ApiResource {

  private static final String CLASS_DEFAULT_PATH = "admin/user";
  private static final String CHANGE_PASSWORD_PATH = "changepassword";
  private static final String LOGOUT_PATH = "logout";

  public enum Status {
    @SerializedName("0")
    DEACTIVE(0),

    @SerializedName("1")
    ACTIVE(1);

    private final int val;

    private Status(int val) {
      this.val = val;
    }

    public int getVal() {
      return val;
    }
  }

  public enum Gender {
    @SerializedName("0")
    MALE(0),

    @SerializedName("1")
    FEMALE(1);

    private final int val;

    private Gender(int val) {
      this.val = val;
    }

    public int getVal() {
      return val;
    }
  }

  public enum Role {
    @SerializedName("0")
    USER(0),

    @SerializedName("1")
    ADMIN(1);

    private final int val;

    private Role(int val) {
      this.val = val;
    }

    public int getVal() {
      return val;
    }
  }

  /**
   * Create an user account for workspace.
   *
   * @param userParams a Map object storing key-value pairs of request parameter
   *
   */
  public static JsonObject create(Map<String, Object> userParams) throws UizaException {
    JsonElement response =
        request(RequestMethod.POST, buildRequestURL(CLASS_DEFAULT_PATH), userParams);
    String id = getId((JsonObject) checkResponseType(response));

    return retrieve(id);
  }

  /**
   * Retrieves the details of an existing user.
   * You need only supply the unique userId that was returned upon user creation.
   *
   * @param id An id of user to retrieve
   *
   */
  public static JsonObject retrieve(String id) throws UizaException {
    if (id == null || id.isEmpty()) {
      throw new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, "", 400);
    }

    Map<String, Object> userParams = new HashMap<>();
    userParams.put("id", id);
    JsonElement response =
        request(RequestMethod.GET, buildRequestURL(CLASS_DEFAULT_PATH), userParams);

    return checkResponseType(response);
  }

  /**
   * Returns a list of your user.
   * The users are returned sorted by creation date, with the most recent user appearing first.
   *
   * If you use Admin token, you will get all the user.
   * If you use User token, you can only get the information of that user.
   */
  public static JsonArray list() throws UizaException {
    JsonElement response = request(RequestMethod.GET, buildRequestURL(CLASS_DEFAULT_PATH), null);

    return checkResponseType(response);
  }

  /**
   * Updates the specified user by setting the values of the parameters passed.
   * Any parameters not provided will be left unchanged.
   *
   * @param id An id of user to update
   * @param userParams a Map object storing key-value pairs of request parameter
   *
   */
  public static JsonObject update(String id, Map<String, Object> userParams) throws UizaException {
    if (userParams == null) {
      userParams = new HashMap<>();
    }
    userParams.put("id", id);
    request(RequestMethod.PUT, buildRequestURL(CLASS_DEFAULT_PATH), userParams);

    return retrieve(id);
  }

  /**
   * Permanently deletes a user. It cannot be undone.
   * Also immediately cancels all token & information of this user.
   *
   * @param id An id of user to delete
   *
   */
  public static JsonObject delete(String id) throws UizaException {
    Map<String, Object> userParams = new HashMap<>();
    userParams.put("id", id);
    JsonElement response =
        request(RequestMethod.DELETE, buildRequestURL(CLASS_DEFAULT_PATH), userParams);

    return checkResponseType(response);
  }

  /**
   * Allows Admin or User update their current password.
   *
   * @param id An id of user to change password
   * @param userParams a Map object storing key-value pairs of request parameter
   *
   */
  public static JsonObject changePassword(String id, Map<String, Object> userParams)
      throws UizaException {
    String path_extension = String.format("%s/%s", CLASS_DEFAULT_PATH, CHANGE_PASSWORD_PATH);
    if (userParams == null) {
      userParams = new HashMap<>();
    }
    userParams.put("id", id);
    JsonElement response = request(RequestMethod.POST, buildRequestURL(path_extension), userParams);

    return checkResponseType(response);
  }

  /**
   * Log out an user.
   * After logged out, token will be removed.
   *
   * @param userParams a Map object storing key-value pairs of request parameter
   *
   */
  public static JsonObject logout() throws UizaException {
    String path_extension = String.format("%s/%s", CLASS_DEFAULT_PATH, LOGOUT_PATH);
    JsonElement response = request(RequestMethod.POST, buildRequestURL(path_extension), null);

    return checkResponseType(response);
  }
}
