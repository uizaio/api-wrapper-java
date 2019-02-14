package co.uiza.apiwrapper.exception;

public class ServerException extends UizaException {

  private static final long serialVersionUID = 1L;

  public ServerException(String message, String requestId, Integer statusCode) {
    super(message, requestId, statusCode);
  }
}
