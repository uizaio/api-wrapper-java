package co.uiza.apiwrapper.exception;

public class UnauthorizedException extends UizaException {

  private static final long serialVersionUID = 1L;

  public UnauthorizedException(String message, String requestId, Integer statusCode) {
    super(message, requestId, statusCode);
  }
}
