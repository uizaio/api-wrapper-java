package co.uiza.apiwrapper.exception;

public class ClientException extends UizaException {

  private static final long serialVersionUID = 1L;

  public ClientException(String message, String requestId, Integer statusCode) {
    super(message, requestId, statusCode);
  }

  public ClientException(String message, String requestId, Integer statusCode,
      String descriptionLink) {
    super(message, requestId, statusCode, descriptionLink);
  }
}
