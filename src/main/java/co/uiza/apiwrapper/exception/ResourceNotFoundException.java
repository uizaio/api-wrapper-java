package co.uiza.apiwrapper.exception;

public class ResourceNotFoundException extends UizaException {

  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException(String message, String requestId, Integer statusCode) {
    super(message, requestId, statusCode);
  }
}
