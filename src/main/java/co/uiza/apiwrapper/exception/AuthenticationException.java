package co.uiza.apiwrapper.exception;

public class AuthenticationException extends UizaException {

  private static final long serialVersionUID = 1L;

  public AuthenticationException(String message, String requestId, Integer statusCode) {
    super(message, requestId, statusCode);
  }
}
