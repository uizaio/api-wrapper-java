package io.uiza;

import java.net.PasswordAuthentication;
import java.net.Proxy;

public abstract class Uiza {

  private static final int DEFAULT_CONNECT_TIMEOUT = 30 * 1000;
  private static final int DEFAULT_READ_TIMEOUT = 80 * 1000;
  private static final String VERSION = "1.2.0";

  private static volatile String workspaceApiDomain = "https://stag-ap-southeast-1-api.uizadev.io";
  private static volatile String apiVersion = VERSION;

  public static volatile String authorization;
  public static volatile String appId;

  private static volatile int connectTimeout = -1;
  private static volatile int readTimeout = -1;
  private static volatile Proxy connectionProxy = null;
  private static volatile PasswordAuthentication proxyCredential = null;

  /**
   * Returns the connection timeout.
   *
   * @return timeout value in milliseconds
   */
  public static int getConnectTimeout() {
    if (connectTimeout == -1) {
      return DEFAULT_CONNECT_TIMEOUT;
    }
    return connectTimeout;
  }

  /**
   * Sets the timeout value that will be used for making new connections to the Uiza API (in
   * milliseconds).
   *
   * @param timeout timeout value in milliseconds
   */
  public static void setConnectTimeout(final int timeout) {
    connectTimeout = timeout;
  }

  /**
   * Returns the read timeout.
   *
   * @return timeout value in milliseconds
   */
  public static int getReadTimeout() {
    if (readTimeout == -1) {
      return DEFAULT_READ_TIMEOUT;
    }
    return readTimeout;
  }

  /**
   * Sets the timeout value that will be used when reading data from an established connection to
   * the Uiza API (in milliseconds).
   *
   * <p>
   * Note that this value should be set conservatively because some API requests can take time and a
   * short timeout increases the likelihood of causing a problem in the backend.
   *
   * @param timeout timeout value in milliseconds
   */
  public static void setReadTimeout(final int timeout) {
    readTimeout = timeout;
  }

  /**
   * Set proxy to tunnel all Uiza connections.
   *
   * @param proxy proxy host and port setting
   */
  public static void setConnectionProxy(final Proxy proxy) {
    connectionProxy = proxy;
  }

  /**
   * @return proxy to tunnel all Uiza connections.
   */
  public static Proxy getConnectionProxy() {
    return connectionProxy;
  }

  /**
   * Provide credential for proxy authorization if required.
   *
   * @param auth proxy required username and password
   */
  public static void setProxyCredential(final PasswordAuthentication auth) {
    proxyCredential = auth;
  }

  /**
   * @return proxy credential for proxy authorization
   */
  public static PasswordAuthentication getProxyCredential() {
    return proxyCredential;
  }

  /**
   * @return the workspace API domain
   */
  public static String getWorkspaceApiDomain() {
    return workspaceApiDomain;
  }

  /**
   * @return the API version
   */
  public static String getApiVersion() {
    return apiVersion;
  }
}
