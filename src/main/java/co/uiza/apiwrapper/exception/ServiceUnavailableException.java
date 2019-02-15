package co.uiza.apiwrapper.exception;

public class ServiceUnavailableException extends UizaException {

  private static final long serialVersionUID = 1L;

  public ServiceUnavailableException(String message, String requestId, Integer statusCode) {
    super(message, requestId, statusCode);
  }
}
