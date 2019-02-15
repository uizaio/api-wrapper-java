package co.uiza.apiwrapper.exception;

public class BadRequestException extends UizaException {

  private static final long serialVersionUID = 1L;

  public BadRequestException(String message, String requestId, Integer statusCode) {
    super(message, requestId, statusCode);
  }
}
