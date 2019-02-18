package co.uiza.apiwrapper.exception;

public class UnprocessableException extends UizaException {

  private static final long serialVersionUID = 1L;

  public UnprocessableException(String message, String requestId, Integer statusCode) {
    super(message, requestId, statusCode);
  }

  public UnprocessableException(String message, String requestId, Integer statusCode,
      String descriptionLink) {
    super(message, requestId, statusCode, descriptionLink);
  }
}
