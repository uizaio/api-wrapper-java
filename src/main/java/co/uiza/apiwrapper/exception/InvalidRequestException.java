package co.uiza.apiwrapper.exception;

public class InvalidRequestException extends UizaException {

  private static final long serialVersionUID = 1L;
  private final String param;

  public InvalidRequestException(String message, String param, String requestId, Integer statusCode,
      Throwable e) {
    super(message, requestId, statusCode, e);
    this.param = param;
  }

  public String getParam() {
    return param;
  }
}