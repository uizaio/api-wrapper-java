package io.uiza.model;

import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import io.uiza.exception.UizaException;
import io.uiza.net.ApiResource;

/**
 * This API wrapper helps you monitor the four key dimensions of video QoS: playback failures,
 * startup time, re-buffering, and video quality.
 */
public class Analytic extends ApiResource {

  private static final String PATH_EXTENSION_FORMAT = "%s/%s";
  private static final String CLASS_DEFAULT_PATH = "analytic/entity/video-quality";
  private static final String TOTAL_LINE_PATH = "total-line-v2";
  private static final String TYPE_PATH = "type";
  private static final String LINE_PATH = "line";

  public enum DescriptionLink {
    GET_TOTAL_LINE("https://docs.uiza.io/#total-line"),

    GET_TYPE("https://docs.uiza.io/#type"),

    GET_LINE("https://docs.uiza.io/#line");

    private final String val;

    private DescriptionLink(String val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return val;
    }
  }

  public enum Metric {
    PALYBACK_FAILURE_PERCENTAGE("playback_failure_percentage"),

    VIDEO_STARTUP_TIME("video_startup_time"),

    AGGREGATE_STARTUP_TIME("aggregate_startup_time"),

    EXITS_BEFORE_VIDEO_START("exits_before_video_start"),

    REBUFFER_PERCENTAGE("rebuffer_percentage"),

    REBUFFER_FREQUENCY("rebuffer_frequency"),

    PLAYBACK_FAILURE_SCORE("playback_failure_score"),

    REBUFFER_COUNT("rebuffer_count"),

    REBUFFER_DURATION("rebuffer_duration"),

    UPSCALE_PERCENTAGE("upscale_percentage"),

    DOWNSCALE_PERCENTAGE("downscale_percentage"),

    MAX_UPSCALE_PERCENTAGE("max_upscale_percentage"),

    MAX_DOWNSCALE_PERCENTAGE("max_downscale_percentage");

    private final String val;

    private Metric(String val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return val;
    }
  }

  public enum TypeFilter {
    COUNTRY("country"),

    DEVICE("device"),

    TITLE("title"),

    PLAYER("player"),

    OS("os");

    private final String val;

    private TypeFilter(String val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return val;
    }
  }

  /**
   * Get data grouped by hour (data refresh every 5 minutes). Track video playback on any metric
   * performance, so you can know exactly what’s happening on every user’s device and debug more
   * effectively.
   *
   * @param lineParams a Map object storing key-value pairs of request parameter
   * @return total lines of analytic
   * @throws UizaException
   */
  public static JsonArray getTotalLine(Map<String, Object> lineParams) throws UizaException {
    String pathExtension =
        String.format(PATH_EXTENSION_FORMAT, CLASS_DEFAULT_PATH, TOTAL_LINE_PATH);
    JsonElement response = request(RequestMethod.GET, buildRequestURL(pathExtension), lineParams,
        DescriptionLink.GET_TOTAL_LINE.toString());

    return checkResponseType(response);
  }

  /**
   * Get data base on 4 type of filter: country, device, title, player.
   *
   * @param lineParams a Map object storing key-value pairs of request parameter
   * @return type of analytic
   * @throws UizaException
   */
  public static JsonArray getType(Map<String, Object> lineParams) throws UizaException {
    String pathExtension = String.format(PATH_EXTENSION_FORMAT, CLASS_DEFAULT_PATH, TYPE_PATH);
    JsonElement response = request(RequestMethod.GET, buildRequestURL(pathExtension), lineParams,
        DescriptionLink.GET_TYPE.toString());

    return checkResponseType(response);
  }

  /**
   * Get data grouped by hour. Get total view in time range. This help you to draw a line chart to
   * visualize data.
   *
   * @param lineParams a Map object storing key-value pairs of request parameter
   * @return line of analytic
   * @throws UizaException
   */
  public static JsonArray getLine(Map<String, Object> lineParams) throws UizaException {
    String pathExtension = String.format(PATH_EXTENSION_FORMAT, CLASS_DEFAULT_PATH, LINE_PATH);
    JsonElement response = request(RequestMethod.GET, buildRequestURL(pathExtension), lineParams,
        DescriptionLink.GET_LINE.toString());

    return checkResponseType(response);
  }
}
