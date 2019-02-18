package co.uiza.apiwrapper.model;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import co.uiza.apiwrapper.exception.BadRequestException;
import co.uiza.apiwrapper.exception.UizaException;
import co.uiza.apiwrapper.net.ApiResource;
import co.uiza.apiwrapper.net.util.ErrorMessage;

public class Storage extends ApiResource {

  private static final String CLASS_DEFAULT_PATH = "media/storage";

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
