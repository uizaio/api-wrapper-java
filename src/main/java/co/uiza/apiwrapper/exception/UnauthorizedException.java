package co.uiza.apiwrapper.exception;

public class UnauthorizedException extends UizaException {

  private static final long serialVersionUID = 1L;

  public UnauthorizedException(String message, String requestId, Integer statusCode) {
    super(message, requestId, statusCode);
  }

  public UnauthorizedException(String message, String requestId, Integer statusCode,
      String descriptionLink) {
    super(message, requestId, statusCode, descriptionLink);
  }
}
