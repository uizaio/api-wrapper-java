package co.uiza.apiwrapper.exception;

public class InternalServerException extends UizaException {

  private static final long serialVersionUID = 1L;

  public InternalServerException(String message, String requestId, Integer statusCode) {
    super(message, requestId, statusCode);
  }
}
