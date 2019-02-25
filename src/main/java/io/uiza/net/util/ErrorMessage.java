package io.uiza.net.util;

import io.uiza.net.ApiResource;

public class ErrorMessage {
  public static final String BAD_REQUEST_ERROR =
      "The request was unacceptable, often due to missing a required parameter.";
  public static final String CLIENT_ERROR = "The error seems to have been caused by the client.";
  public static final String ENCODE_FAILED =
      String.format("Unable to encode parameters to %s", ApiResource.CHARSET);
  public static final String INTERNAL_SERVER_ERROR =
      "We had a problem with our server. Try again later.";
  public static final String INVALID_REQUEST_METHOD = "Unrecognized HTTP method";
  public static final String INVALID_REQUEST_TYPE = "Invalid ApiResource request type.";
  public static final String INVALID_RESPONSE =
      "Invalid response object from API: %s. (HTTP response code was %d)";
  public static final String NOT_FOUND_ERROR = "The requested resource does not exist.";
  public static final String SERVER_ERROR = "The server is aware that it has encountered an error.";
  public static final String SERVICE_UNAVAILABLE_ERROR =
      "The server is overloaded or down for maintenance.";
  public static final String UNAUTHORIZED_ERROR =
      "No API key provided. (HINT: set your API key using 'Uiza.apiKey = <API-KEY>'.";
  public static final String UNPROCESSABLE_ERROR =
      "The syntax of the request is incorrect (often cause of wrong parameter)";
}
