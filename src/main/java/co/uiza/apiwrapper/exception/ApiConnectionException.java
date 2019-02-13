package co.uiza.apiwrapper.exception;

public class ApiConnectionException extends UizaException {

  private static final long serialVersionUID = 1L;

  public ApiConnectionException(String message) {
    this(message, null);
  }

  public ApiConnectionException(String message, Throwable e) {
    super(message, null, 0, e);
  }
}
