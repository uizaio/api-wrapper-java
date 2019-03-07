package io.uiza.net.util;

import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UizaResponse {

  int code;
  String body;
  UizaHeaders headers;

  /**
   * Constructs a Uiza response with the specified status code and body.
   */
  public UizaResponse(int code, String body) {
    this.code = code;
    this.body = body;
    headers = null;
  }

  public UizaResponse(int code, String body, Map<String, List<String>> headers) {
    this.code = code;
    this.body = body;
    this.headers = new UizaHeaders(headers);
  }

  /**
   * @return the status code of API HTTP response
   */
  public int code() {
    return code;
  }

  /**
   * @return the body of API HTTP response
   */
  public String body() {
    return body;
  }

  /**
   * @return headers of API HTTP response
   */
  public UizaHeaders headers() {
    return headers;
  }

  /**
   * @return request ID through API HTTP response
   */
  public String requestId() {
    if (body == null) {
      return null;
    }

    JsonParser parser = new JsonParser();
    JsonObject bodyObject = parser.parse(body).getAsJsonObject();

    return bodyObject.get("requestId").getAsString();
  }
}
