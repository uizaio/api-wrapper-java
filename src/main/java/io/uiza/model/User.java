package io.uiza.model;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.uiza.exception.BadRequestException;
import io.uiza.exception.UizaException;
import io.uiza.net.ApiResource;
import io.uiza.net.util.ErrorMessage;

/**
 * This API wrapper helps you managing users of 2 level: Admin and User.
 */
public class User extends ApiResource {

  private static final String PATH_EXTENSION_FORMAT = "%s/%s";
  private static final String CLASS_DEFAULT_PATH = "admin/user";
  private static final String CHANGE_PASSWORD_PATH = "changepassword";
  private static final String LOGOUT_PATH = "logout";

  public enum DescriptionLink {
    CREATE("https://docs.uiza.io/#create-an-user"),

    RETRIEVE("https://docs.uiza.io/#retrieve-an-user"),

    LIST("https://docs.uiza.io/#list-all-users"),

    UPDATE("https://docs.uiza.io/#update-an-user"),

    DELETE("https://docs.uiza.io/#delete-an-user"),

    CHANGE_PASSWORD("https://docs.uiza.io/#update-password"),

    LOGOUT("https://docs.uiza.io/#log-out");

    private final String val;

    private DescriptionLink(String val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return val;
    }
  }

  public enum Status {
    DEACTIVE(0), ACTIVE(1);

    private final int val;

    private Status(int val) {
      this.val = val;
    }

    public int getVal() {
      return val;
    }
  }

  public enum Gender {
    MALE(0), FEMALE(1);

    private final int val;

    private Gender(int val) {
      this.val = val;
    }

    public int getVal() {
      return val;
    }
  }

  public enum Role {
    USER(0), ADMIN(1);

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
   * @return created user JSON object
   * @throws UizaException
   */
  public static JsonObject create(Map<String, Object> userParams) throws UizaException {
    JsonElement response = request(RequestMethod.POST, buildRequestURL(CLASS_DEFAULT_PATH),
        userParams, DescriptionLink.CREATE.toString());
    String id = getId((JsonObject) checkResponseType(response));

    return retrieve(id);
  }

  /**
   * Retrieves the details of an existing user. You need only supply the unique userId that was
   * returned upon user creation.
   *
   * @param id An id of a user to retrieve
   * @return a user JSON object with matched id
   * @throws UizaException
   */
  public static JsonObject retrieve(String id) throws UizaException {
    if (id == null || id.isEmpty()) {
      throw new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, "", 400,
          DescriptionLink.RETRIEVE.toString());
    }

    Map<String, Object> userParams = new HashMap<>();
    userParams.put("id", id);
    JsonElement response = request(RequestMethod.GET, buildRequestURL(CLASS_DEFAULT_PATH),
        userParams, DescriptionLink.RETRIEVE.toString());

    return checkResponseType(response);
  }

  /**
   * Returns a list of your user. The users are returned sorted by creation date, with the most
   * recent user appearing first.
   *
   * If you use Admin token, you will get all the user. If you use User token, you can only get the
   * information of that user.
   *
   * @return a list of users
   * @throws UizaException
   */
  public static JsonArray list() throws UizaException {
    JsonElement response = request(RequestMethod.GET, buildRequestURL(CLASS_DEFAULT_PATH), null,
        DescriptionLink.LIST.toString());

    return checkResponseType(response);
  }

  /**
   * Updates the specified user by setting the values of the parameters passed. Any parameters not
   * provided will be left unchanged.
   *
   * @param id An id of a user to update
   * @param userParams a Map object storing key-value pairs of request parameter
   * @return updated user JSON object
   * @throws UizaException
   */
  public static JsonObject update(String id, Map<String, Object> userParams) throws UizaException {
    if (userParams == null) {
      userParams = new HashMap<>();
    }
    userParams.put("id", id);
    request(RequestMethod.PUT, buildRequestURL(CLASS_DEFAULT_PATH), userParams,
        DescriptionLink.UPDATE.toString());

    return retrieve(id);
  }

  /**
   * Permanently deletes a user. It cannot be undone. Also immediately cancels all token and
   * information of this user.
   *
   * @param id An id of a user to delete
   * @return id of the deleted user
   * @throws UizaException
   */
  public static JsonObject delete(String id) throws UizaException {
    Map<String, Object> userParams = new HashMap<>();
    userParams.put("id", id);
    JsonElement response = request(RequestMethod.DELETE, buildRequestURL(CLASS_DEFAULT_PATH),
        userParams, DescriptionLink.DELETE.toString());

    return checkResponseType(response);
  }

  /**
   * Allows Admin or User update their current password.
   *
   * @param id An id of a user to change password
   * @param userParams a Map object storing key-value pairs of request parameter
   * @return success if user password has been updated
   * @throws UizaException
   */
  public static JsonObject changePassword(String id, Map<String, Object> userParams)
      throws UizaException {
    String pathExtension =
        String.format(PATH_EXTENSION_FORMAT, CLASS_DEFAULT_PATH, CHANGE_PASSWORD_PATH);
    if (userParams == null) {
      userParams = new HashMap<>();
    }
    userParams.put("id", id);
    JsonElement response = request(RequestMethod.PUT, buildRequestURL(pathExtension), userParams,
        DescriptionLink.CHANGE_PASSWORD.toString());

    return checkResponseType(response);
  }

  /**
   * Log out an user. After logged out, token will be removed.
   *
   * @return logout successfully
   * @throws UizaException
   */
  public static JsonObject logout() throws UizaException {
    String pathExtension = String.format(PATH_EXTENSION_FORMAT, CLASS_DEFAULT_PATH, LOGOUT_PATH);
    JsonElement response = request(RequestMethod.POST, buildRequestURL(pathExtension), null,
        DescriptionLink.LOGOUT.toString());

    return checkResponseType(response);
  }
}
