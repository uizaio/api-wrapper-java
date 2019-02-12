package co.uiza.apiwrapper;

public abstract class Uiza {
  private static final int DEFAULT_CONNECT_TIMEOUT = 30 * 1000;
  private static final int DEFAULT_READ_TIMEOUT = 80 * 1000;

  public static final String API_DOMAIN = "https://apiwrapper.uiza.co";
  public static final String VERSION = "1.0.0";

  public static volatile String apiKey;
  public static volatile String apiVersion;
  private static volatile String apiDomain = API_DOMAIN;

  private static volatile int connectTimeout = -1;
  private static volatile int readTimeout = -1;

  public static String getApiDomain() {
    return apiDomain;
  }

  public static int getConnectTimeout() {
    if (connectTimeout == -1) {
      return DEFAULT_CONNECT_TIMEOUT;
    }
    return connectTimeout;
  }

  public static void setConnectTimeout(final int timeout) {
    connectTimeout = timeout;
  }

  public static int getReadTimeout() {
    if (readTimeout == -1) {
      return DEFAULT_READ_TIMEOUT;
    }
    return readTimeout;
  }

  public static void setReadTimeout(final int timeout) {
    readTimeout = timeout;
  }
}
