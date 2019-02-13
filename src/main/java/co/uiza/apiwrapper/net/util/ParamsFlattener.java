package co.uiza.apiwrapper.net.util;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import co.uiza.apiwrapper.exception.InvalidRequestException;

public class ParamsFlattener {

  /**
   *
   * Convert Map object into request url parameters
   *
   * @param params The Map object to flatten
   * @return a List of Parameter objects
   */
  public static List<Parameter> flattenParams(Map<String, Object> params)
      throws InvalidRequestException {
    return flattenParamsMap(params, null);
  }

  private static List<Parameter> flattenParamsMap(Map<String, Object> params, String keyPrefix)
      throws InvalidRequestException {
    List<Parameter> flatParams = new ArrayList<>();
    if (params == null) {
      return flatParams;
    }

    for (Map.Entry<String, Object> entry : params.entrySet()) {
      String key = entry.getKey();
      Object value = entry.getValue();

      String newPrefix = key;
      if (keyPrefix != null) {
        newPrefix = String.format("%s[%s]", keyPrefix, key);
      }

      flatParams.addAll(flattenParamsValue(value, newPrefix));
    }

    return flatParams;
  }

  @SuppressWarnings("unchecked")
  private static List<Parameter> flattenParamsValue(Object value, String keyPrefix)
      throws InvalidRequestException {
    List<Parameter> flatParams;

    if (value instanceof Map<?, ?>) {
      flatParams = flattenParamsMap((Map<String, Object>) value, keyPrefix);
    } else if (value instanceof List<?>) {
      flatParams = flattenParamsList((List<Object>) value, keyPrefix);
    } else if (value instanceof Object[]) {
      flatParams = flattenParamsArray((Object[]) value, keyPrefix);
    } else if ("".equals(value)) {
      throw new InvalidRequestException("You cannot set '" + keyPrefix + "' to an empty string. "
          + "We interpret empty strings as null in requests. " + "You may set '" + keyPrefix
          + "' to null to delete the property.", keyPrefix, null, 0, null);
    } else if (value == null) {
      flatParams = new ArrayList<>();
      flatParams.add(new Parameter(keyPrefix, ""));
    } else {
      flatParams = new ArrayList<>();
      flatParams.add(new Parameter(keyPrefix, value.toString()));
    }

    return flatParams;
  }

  private static List<Parameter> flattenParamsList(List<Object> params, String keyPrefix)
      throws InvalidRequestException {
    List<Parameter> flatParams = new ArrayList<>();
    ListIterator<?> it = ((List<?>) params).listIterator();
    if (params.isEmpty()) {
      flatParams.add(new Parameter(keyPrefix, ""));
    } else {
      while (it.hasNext()) {
        String newPrefix = String.format("%s[%d]", keyPrefix, it.nextIndex());
        flatParams.addAll(flattenParamsValue(it.next(), newPrefix));
      }
    }

    return flatParams;
  }

  private static List<Parameter> flattenParamsArray(Object[] params, String keyPrefix)
      throws InvalidRequestException {
    List<Parameter> flatParams = new ArrayList<>();
    if (params.length == 0) {
      flatParams.add(new Parameter(keyPrefix, ""));
    } else {
      for (int i = 0; i < params.length; i++) {
        String newPrefix = String.format("%s[%d]", keyPrefix, i);
        flatParams.addAll(flattenParamsValue(params[i], newPrefix));
      }
    }

    return flatParams;
  }
}
