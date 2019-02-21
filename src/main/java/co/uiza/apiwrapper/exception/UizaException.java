package co.uiza.apiwrapper.exception;

public class UizaException extends Exception {

  private static final long serialVersionUID = 1L;
  private String requestId;
  private Integer statusCode;
  private String descriptionLink;

  public UizaException(String message, String requestId, Integer statusCode) {
    this(message, requestId, statusCode, null);
  }

  public UizaException(String message, String requestId, Integer statusCode,
      String descriptionLink) {
    this(message, requestId, statusCode, descriptionLink, null);
  }

  /**
   * Constructs a new Uiza exception with the specified details.
   */
  public UizaException(String message, String requestId, Integer statusCode, String descriptionLink,
      Throwable e) {
    super(message, e);
    this.requestId = requestId;
    this.statusCode = statusCode;
    this.descriptionLink = descriptionLink;
  }

  public String getRequestId() {
    return requestId;
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  public String getDescriptionLink() {
    return descriptionLink;
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
    if (descriptionLink != null) {
      additionalInfo += "; description-link: " + descriptionLink;
    }
    return super.toString() + additionalInfo;
  }
}
