package co.uiza.apiwrapper.net.util;

import co.uiza.apiwrapper.net.ApiResource;

public class ErrorMessage {
  public static final String ENCODE_FAILED =
      String.format("Unable to encode parameters to %s", ApiResource.CHARSET);
  public static final String INCORRECT_SYNTAX =
      "The syntax of the request is incorrect (often cause of wrong parameter)";
  public static final String INTERNAL_SERVER_ERROR =
      "We had a problem with our server. Try again later.";
  public static final String INVALID_REQUEST_METHOD = "Unrecognized HTTP method";
  public static final String INVALID_REQUEST_TYPE = "Invalid ApiResource request type.";
  public static final String INVALID_RESPONSE =
      "Invalid response object from API: %s. (HTTP response code was %d)";
  public static final String IOEXCEPTION = "IOException during API request to Uiza";
  public static final String NO_API_KEY =
      "No API key provided. (HINT: set your API key using 'Uiza.apiKey = <API-KEY>'.";
  public static final String RESOURCE_NOT_FOUND = "The requested resource does not exist.";
  public static final String SERVICE_UNAVAILABLE =
      "The server is overloaded or down for maintenance.";
}
