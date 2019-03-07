package io.uiza.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * Wraps a SSLSocketFactory and enables more TLS versions.
 */
public class UizaSslSocketFactory extends SSLSocketFactory {

  private final SSLSocketFactory under;
  private final boolean tlsv11Supported;
  private final boolean tlsv12Supported;

  private static final String TLS_V11 = "TLSv1.1";
  private static final String TLS_V12 = "TLSv1.2";

  /**
   * Constructs a new SSL socket factory.
   */
  public UizaSslSocketFactory() {
    under = HttpsURLConnection.getDefaultSSLSocketFactory();

    boolean tlsv11Supported = false;
    boolean tlsv12Supported = false;

    String[] supportedProtos = new String[0];
    try {
      supportedProtos = SSLContext.getDefault().getSupportedSSLParameters().getProtocols();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    for (String proto : supportedProtos) {
      if (proto.equals(TLS_V11)) {
        tlsv11Supported = true;
      } else if (proto.equals(TLS_V12)) {
        tlsv12Supported = true;
      }
    }

    this.tlsv11Supported = tlsv11Supported;
    this.tlsv12Supported = tlsv12Supported;
  }

  private Socket fixupSocket(Socket sock) {
    if (!(sock instanceof SSLSocket)) {
      return sock;
    }

    SSLSocket sslSock = (SSLSocket) sock;

    Set<String> protos = new HashSet<>(Arrays.asList(sslSock.getEnabledProtocols()));
    if (tlsv11Supported) {
      protos.add(TLS_V11);
    }
    if (tlsv12Supported) {
      protos.add(TLS_V12);
    }

    sslSock.setEnabledProtocols(protos.toArray(new String[0]));
    return sslSock;
  }

  @Override
  public String[] getDefaultCipherSuites() {
    return under.getDefaultCipherSuites();
  }

  @Override
  public String[] getSupportedCipherSuites() {
    return under.getSupportedCipherSuites();
  }

  @Override
  public Socket createSocket(Socket s, String host, int port, boolean autoClose)
      throws IOException {
    return fixupSocket(under.createSocket(s, host, port, autoClose));
  }

  @Override
  public Socket createSocket(String host, int port) throws IOException {
    return fixupSocket(under.createSocket(host, port));
  }

  @Override
  public Socket createSocket(String host, int port, InetAddress localHost, int localPort)
      throws IOException {
    return fixupSocket(under.createSocket(host, port, localHost, localPort));
  }

  @Override
  public Socket createSocket(InetAddress host, int port) throws IOException {
    return fixupSocket(under.createSocket(host, port));
  }

  @Override
  public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort)
      throws IOException {
    return fixupSocket(under.createSocket(address, port, localAddress, localPort));
  }
}
