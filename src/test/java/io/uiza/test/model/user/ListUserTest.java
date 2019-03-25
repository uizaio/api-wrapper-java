package io.uiza.test.model.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.gson.JsonArray;

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
public class ListUserTest extends TestBase {

  @Before
  public void setUp() throws Exception {
    PowerMockito.mockStatic(ApiResource.class);
    Mockito.when(ApiResource.buildRequestURL(Mockito.any())).thenReturn(TEST_URL);
  }

  @Test
  public void testSuccess() throws UizaException {
    JsonArray expected = new JsonArray();

    Mockito
        .when(
            ApiResource.request(RequestMethod.GET, TEST_URL, null, DescriptionLink.LIST.toString()))
        .thenReturn(expected);
    Mockito.when(ApiResource.checkResponseType(Mockito.any())).thenCallRealMethod();

    JsonArray actual = User.list();
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testFailedThrowsBadRequestException() throws UizaException {
    UizaException e = new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, "", 400,
        DescriptionLink.LIST.toString());

    Mockito
        .when(
            ApiResource.request(RequestMethod.GET, TEST_URL, null, DescriptionLink.LIST.toString()))
        .thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    User.list();
  }

  @Test
  public void testFailedThrowsUnauthorizedException() throws UizaException {
    UizaException e = new UnauthorizedException(ErrorMessage.UNAUTHORIZED_ERROR, "", 401,
        DescriptionLink.LIST.toString());

    Mockito
        .when(
            ApiResource.request(RequestMethod.GET, TEST_URL, null, DescriptionLink.LIST.toString()))
        .thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    User.list();
  }

  @Test
  public void testFailedThrowsNotFoundException() throws UizaException {
    UizaException e = new NotFoundException(ErrorMessage.NOT_FOUND_ERROR, "", 404,
        DescriptionLink.LIST.toString());

    Mockito
        .when(
            ApiResource.request(RequestMethod.GET, TEST_URL, null, DescriptionLink.LIST.toString()))
        .thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    User.list();
  }

  @Test
  public void testFailedThrowsUnprocessableException() throws UizaException {
    UizaException e = new UnprocessableException(ErrorMessage.UNPROCESSABLE_ERROR, "", 422,
        DescriptionLink.LIST.toString());

    Mockito
        .when(
            ApiResource.request(RequestMethod.GET, TEST_URL, null, DescriptionLink.LIST.toString()))
        .thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    User.list();
  }

  @Test
  public void testFailedThrowsInternalServerException() throws UizaException {
    UizaException e = new InternalServerException(ErrorMessage.INTERNAL_SERVER_ERROR, "", 500,
        DescriptionLink.LIST.toString());

    Mockito
        .when(
            ApiResource.request(RequestMethod.GET, TEST_URL, null, DescriptionLink.LIST.toString()))
        .thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    User.list();
  }

  @Test
  public void testFailedThrowsServiceUnavailableException() throws UizaException {
    UizaException e = new ServiceUnavailableException(ErrorMessage.SERVICE_UNAVAILABLE_ERROR, "",
        503, DescriptionLink.LIST.toString());

    Mockito
        .when(
            ApiResource.request(RequestMethod.GET, TEST_URL, null, DescriptionLink.LIST.toString()))
        .thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    User.list();
  }

  @Test
  public void testFailedThrowsClientException() throws UizaException {
    UizaException e =
        new ClientException(ErrorMessage.CLIENT_ERROR, "", 450, DescriptionLink.LIST.toString());

    Mockito
        .when(
            ApiResource.request(RequestMethod.GET, TEST_URL, null, DescriptionLink.LIST.toString()))
        .thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    User.list();
  }

  @Test
  public void testFailedThrowsServerException() throws UizaException {
    UizaException e =
        new ServerException(ErrorMessage.SERVER_ERROR, "", 550, DescriptionLink.LIST.toString());

    Mockito
        .when(
            ApiResource.request(RequestMethod.GET, TEST_URL, null, DescriptionLink.LIST.toString()))
        .thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    User.list();
  }

  @Test
  public void testFailedThrowsUizaException() throws UizaException {
    UizaException e = new UizaException("", "", 300, DescriptionLink.LIST.toString());

    Mockito
        .when(
            ApiResource.request(RequestMethod.GET, TEST_URL, null, DescriptionLink.LIST.toString()))
        .thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    User.list();
  }
}
