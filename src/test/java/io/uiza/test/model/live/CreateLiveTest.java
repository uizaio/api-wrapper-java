package io.uiza.test.model.live;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.gson.JsonObject;

import io.uiza.exception.BadRequestException;
import io.uiza.exception.ClientException;
import io.uiza.exception.InternalServerException;
import io.uiza.exception.NotFoundException;
import io.uiza.exception.ServerException;
import io.uiza.exception.ServiceUnavailableException;
import io.uiza.exception.UizaException;
import io.uiza.exception.UnauthorizedException;
import io.uiza.exception.UnprocessableException;
import io.uiza.model.Live;
import io.uiza.model.Live.DescriptionLink;
import io.uiza.net.ApiResource;
import io.uiza.net.ApiResource.RequestMethod;
import io.uiza.net.util.ErrorMessage;
import io.uiza.test.TestBase;

@PowerMockIgnore("javax.net.ssl.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest(ApiResource.class)
public class CreateLiveTest extends TestBase {

  @Before
  public void setUp() throws Exception {
    PowerMockito.mockStatic(ApiResource.class);
    Mockito.when(ApiResource.buildRequestURL(Mockito.any())).thenReturn(TEST_URL);
  }

  @Test
  public void testSuccess() throws UizaException {
    JsonObject expectedOfCreate = new JsonObject();
    expectedOfCreate.addProperty("id", LIVE_ID);

    Map<String, Object> paramsOfCreate = new HashMap<>();
    paramsOfCreate.put("name", "Name");
    paramsOfCreate.put("mode", "push");
    paramsOfCreate.put("encode", 0);
    paramsOfCreate.put("dvr", 0);
    paramsOfCreate.put("linkStream", "");
    paramsOfCreate.put("resourceMode", "single");

    JsonObject expectedOfRetrieve = new JsonObject();
    expectedOfRetrieve.addProperty("id", LIVE_ID);
    expectedOfRetrieve.addProperty("name", "Name");

    Map<String, Object> paramsOfRetrieve = new HashMap<>();
    paramsOfRetrieve.put("id", LIVE_ID);

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, paramsOfCreate,
        DescriptionLink.CREATE.toString())).thenReturn(expectedOfCreate);
    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, paramsOfRetrieve,
        DescriptionLink.RETRIEVE.toString())).thenReturn(expectedOfRetrieve);
    Mockito.when(ApiResource.getId(Mockito.any())).thenCallRealMethod();
    Mockito.when(ApiResource.checkResponseType(Mockito.any())).thenCallRealMethod();

    JsonObject actual = Live.create(paramsOfCreate);
    Assert.assertEquals(expectedOfRetrieve, actual);
  }

  @Test
  public void testFailedThrowsBadRequestException() throws UizaException {
    UizaException e = new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, "", 400,
        DescriptionLink.CREATE.toString());

    Mockito.when(
        ApiResource.request(RequestMethod.POST, TEST_URL, null, DescriptionLink.CREATE.toString()))
        .thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Live.create(null);
  }

  @Test
  public void testFailedThrowsUnauthorizedException() throws UizaException {
    UizaException e = new UnauthorizedException(ErrorMessage.UNAUTHORIZED_ERROR, "", 401,
        DescriptionLink.CREATE.toString());

    Mockito.when(
        ApiResource.request(RequestMethod.POST, TEST_URL, null, DescriptionLink.CREATE.toString()))
        .thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Live.create(null);
  }

  @Test
  public void testFailedThrowsNotFoundException() throws UizaException {
    UizaException e = new NotFoundException(ErrorMessage.NOT_FOUND_ERROR, "", 404,
        DescriptionLink.CREATE.toString());

    Mockito.when(
        ApiResource.request(RequestMethod.POST, TEST_URL, null, DescriptionLink.CREATE.toString()))
        .thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Live.create(null);
  }

  @Test
  public void testFailedThrowsUnprocessableException() throws UizaException {
    UizaException e = new UnprocessableException(ErrorMessage.UNPROCESSABLE_ERROR, "", 422,
        DescriptionLink.CREATE.toString());

    Mockito.when(
        ApiResource.request(RequestMethod.POST, TEST_URL, null, DescriptionLink.CREATE.toString()))
        .thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Live.create(null);
  }

  @Test
  public void testFailedThrowsInternalServerException() throws UizaException {
    UizaException e = new InternalServerException(ErrorMessage.INTERNAL_SERVER_ERROR, "", 500,
        DescriptionLink.CREATE.toString());

    Mockito.when(
        ApiResource.request(RequestMethod.POST, TEST_URL, null, DescriptionLink.CREATE.toString()))
        .thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Live.create(null);
  }

  @Test
  public void testFailedThrowsServiceUnavailableException() throws UizaException {
    UizaException e = new ServiceUnavailableException(ErrorMessage.SERVICE_UNAVAILABLE_ERROR, "",
        503, DescriptionLink.CREATE.toString());

    Mockito.when(
        ApiResource.request(RequestMethod.POST, TEST_URL, null, DescriptionLink.CREATE.toString()))
        .thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Live.create(null);
  }

  @Test
  public void testFailedThrowsClientException() throws UizaException {
    UizaException e =
        new ClientException(ErrorMessage.CLIENT_ERROR, "", 450, DescriptionLink.CREATE.toString());

    Mockito.when(
        ApiResource.request(RequestMethod.POST, TEST_URL, null, DescriptionLink.CREATE.toString()))
        .thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Live.create(null);
  }

  @Test
  public void testFailedThrowsServerException() throws UizaException {
    UizaException e =
        new ServerException(ErrorMessage.SERVER_ERROR, "", 550, DescriptionLink.CREATE.toString());

    Mockito.when(
        ApiResource.request(RequestMethod.POST, TEST_URL, null, DescriptionLink.CREATE.toString()))
        .thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Live.create(null);
  }

  @Test
  public void testFailedThrowsUizaException() throws UizaException {
    UizaException e = new UizaException("", LIVE_ID, 300, DescriptionLink.CREATE.toString());

    Mockito.when(
        ApiResource.request(RequestMethod.POST, TEST_URL, null, DescriptionLink.CREATE.toString()))
        .thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Live.create(null);
  }
}
