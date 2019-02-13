package co.uiza.apiwrapper.exception;

public class ApiException extends UizaException {

  private static final long serialVersionUID = 1L;

  public ApiException(String message, String requestId, Integer statusCode, Throwable e) {
    super(message, requestId, statusCode, e);
  }
}
