package co.uiza.exception;

public class NotFoundException extends UizaException {

  private static final long serialVersionUID = 1L;

  public NotFoundException(String message, String requestId, Integer statusCode) {
    super(message, requestId, statusCode);
  }

  public NotFoundException(String message, String requestId, Integer statusCode,
      String descriptionLink) {
    super(message, requestId, statusCode, descriptionLink);
  }
}
