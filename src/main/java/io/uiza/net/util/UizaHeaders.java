package io.uiza.net.util;

import java.util.List;
import java.util.Map;

public class UizaHeaders {

  Map<String, List<String>> headers;

  public UizaHeaders(Map<String, List<String>> headers) {
    this.headers = headers;
  }

  /**
   * Returns the first header value for a given key.
   *
   * @param name The name of the header key
   * @return the first value for the given key
   */
  public String get(String name) {
    List<String> valuesList = values(name);
    String value = null;
    if (valuesList != null && !valuesList.isEmpty()) {
      value = valuesList.get(0);
    }
    return value;
  }

  public List<String> values(String name) {
    return headers == null ? null : headers.get(name);
  }
}
