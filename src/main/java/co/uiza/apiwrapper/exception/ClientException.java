package co.uiza.apiwrapper.exception;

public class ClientException extends UizaException {

  private static final long serialVersionUID = 1L;

  public ClientException(String message, String requestId, Integer statusCode) {
    super(message, requestId, statusCode);
  }
}
