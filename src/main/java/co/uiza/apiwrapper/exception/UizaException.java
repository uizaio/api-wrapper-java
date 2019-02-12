package co.uiza.apiwrapper.exception;

public abstract class UizaException extends Exception {

  private static final long serialVersionUID = 1L;
  private String requestId;
  private Integer statusCode;

  public UizaException(String message, String requestId, Integer statusCode) {
    this(message, requestId, statusCode, null);
  }

  /**
   * Constructs a new Uiza exception with the specified details.
   */
  public UizaException(String message, String requestId, Integer statusCode, Throwable e) {
    super(message, e);
    this.requestId = requestId;
    this.statusCode = statusCode;
  }

  public String getRequestId() {
    return requestId;
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  /**
   * Returns a description of the exception, including the HTTP status code and request ID (if
   * applicable).
   *
   * @return a string representation of the exception.
   */
  @Override
  public String toString() {
    String additionalInfo = "";
    additionalInfo += "; code: " + statusCode;
    if (requestId != null) {
      additionalInfo += "; request-id: " + requestId;
    }
    return super.toString() + additionalInfo;
  }
}
