package io.uiza.net.util;

import java.util.Date;

public class UizaError {

  private int code;
  private String type;
  private Object data;
  private String message;
  private String requestId;
  private Date datetime;

  /**
   * Constructs a Uiza error with the specified details
   */
  public UizaError(int code, String type, Object data, String message, String requestId,
      Date datetime) {
    this.code = code;
    this.type = type;
    this.data = data;
    this.message = message;
    this.requestId = requestId;
    this.datetime = datetime;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public Date getDatetime() {
    return datetime;
  }

  public void setDatetime(Date datetime) {
    this.datetime = datetime;
  }
}
