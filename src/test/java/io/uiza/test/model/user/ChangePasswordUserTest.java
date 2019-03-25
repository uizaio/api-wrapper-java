package io.uiza.test.model.user;

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
import io.uiza.model.User;
import io.uiza.model.User.DescriptionLink;
import io.uiza.net.ApiResource;
import io.uiza.net.ApiResource.RequestMethod;
import io.uiza.net.util.ErrorMessage;
import io.uiza.test.TestBase;

@PowerMockIgnore("javax.net.ssl.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest(ApiResource.class)
public class ChangePasswordUserTest extends TestBase {

  private Map<String, Object> params;

  @Before
  public void setUp() throws Exception {
    params = new HashMap<>();
    params.put("id", USER_ID);

    PowerMockito.mockStatic(ApiResource.class);
    Mockito.when(ApiResource.buildRequestURL(Mockito.any())).thenReturn(TEST_URL);
  }

  @Test
  public void testInitParamsWhenNull() throws UizaException {
    Map<String, Object> nullParams = null;

    JsonObject expected = new JsonObject();
    expected.addProperty("result", "ok");

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params,
        DescriptionLink.CHANGE_PASSWORD.toString())).thenReturn(expected);
    Mockito.when(ApiResource.checkResponseType(Mockito.any())).thenCallRealMethod();

    JsonObject actual = User.changePassword(USER_ID, nullParams);
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testSuccess() throws UizaException {
    JsonObject expected = new JsonObject();
    expected.addProperty("result", "ok");

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params,
        DescriptionLink.CHANGE_PASSWORD.toString())).thenReturn(expected);
    Mockito.when(ApiResource.checkResponseType(Mockito.any())).thenCallRealMethod();

    JsonObject actual = User.changePassword(USER_ID, params);
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testFailedThrowsBadRequestException() throws UizaException {
    UizaException e = new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, USER_ID, 400,
        DescriptionLink.CHANGE_PASSWORD.toString());

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params,
        DescriptionLink.CHANGE_PASSWORD.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    User.changePassword(USER_ID, params);
  }

  @Test
  public void testFailedThrowsUnauthorizedException() throws UizaException {
    UizaException e = new UnauthorizedException(ErrorMessage.UNAUTHORIZED_ERROR, USER_ID, 401,
        DescriptionLink.CHANGE_PASSWORD.toString());

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params,
        DescriptionLink.CHANGE_PASSWORD.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    User.changePassword(USER_ID, params);
  }

  @Test
  public void testFailedThrowsNotFoundException() throws UizaException {
    UizaException e = new NotFoundException(ErrorMessage.NOT_FOUND_ERROR, USER_ID, 404,
        DescriptionLink.CHANGE_PASSWORD.toString());

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params,
        DescriptionLink.CHANGE_PASSWORD.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    User.changePassword(USER_ID, params);
  }

  @Test
  public void testFailedThrowsUnprocessableException() throws UizaException {
    UizaException e = new UnprocessableException(ErrorMessage.UNPROCESSABLE_ERROR, USER_ID, 422,
        DescriptionLink.CHANGE_PASSWORD.toString());

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params,
        DescriptionLink.CHANGE_PASSWORD.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    User.changePassword(USER_ID, params);
  }

  @Test
  public void testFailedThrowsInternalServerException() throws UizaException {
    UizaException e = new InternalServerException(ErrorMessage.INTERNAL_SERVER_ERROR, USER_ID, 500,
        DescriptionLink.CHANGE_PASSWORD.toString());

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params,
        DescriptionLink.CHANGE_PASSWORD.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    User.changePassword(USER_ID, params);
  }

  @Test
  public void testFailedThrowsServiceUnavailableException() throws UizaException {
    UizaException e = new ServiceUnavailableException(ErrorMessage.SERVICE_UNAVAILABLE_ERROR,
        USER_ID, 503, DescriptionLink.CHANGE_PASSWORD.toString());

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params,
        DescriptionLink.CHANGE_PASSWORD.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    User.changePassword(USER_ID, params);
  }

  @Test
  public void testFailedThrowsClientException() throws UizaException {
    UizaException e = new ClientException(ErrorMessage.CLIENT_ERROR, USER_ID, 450,
        DescriptionLink.CHANGE_PASSWORD.toString());

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params,
        DescriptionLink.CHANGE_PASSWORD.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    User.changePassword(USER_ID, params);
  }

  @Test
  public void testFailedThrowsServerException() throws UizaException {
    UizaException e = new ServerException(ErrorMessage.SERVER_ERROR, USER_ID, 550,
        DescriptionLink.CHANGE_PASSWORD.toString());

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params,
        DescriptionLink.CHANGE_PASSWORD.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    User.changePassword(USER_ID, params);
  }

  @Test
  public void testFailedThrowsUizaException() throws UizaException {
    UizaException e =
        new UizaException("", USER_ID, 300, DescriptionLink.CHANGE_PASSWORD.toString());

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params,
        DescriptionLink.CHANGE_PASSWORD.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    User.changePassword(USER_ID, params);
  }
}
