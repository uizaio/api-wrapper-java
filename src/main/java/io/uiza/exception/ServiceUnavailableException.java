package io.uiza.exception;

public class ServiceUnavailableException extends UizaException {

  private static final long serialVersionUID = 1L;

  public ServiceUnavailableException(String message, String requestId, Integer statusCode) {
    super(message, requestId, statusCode);
  }

  public ServiceUnavailableException(String message, String requestId, Integer statusCode,
      String desctriptionLink) {
    super(message, requestId, statusCode, desctriptionLink);
  }
}
