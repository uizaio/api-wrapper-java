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
import io.uiza.model.User.Gender;
import io.uiza.model.User.Role;
import io.uiza.model.User.Status;
import io.uiza.net.ApiResource;
import io.uiza.net.ApiResource.RequestMethod;
import io.uiza.net.util.ErrorMessage;
import io.uiza.test.TestBase;

@PowerMockIgnore("javax.net.ssl.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest(ApiResource.class)
public class CreateUserTest extends TestBase {

  @Before
  public void setUp() throws Exception {
    PowerMockito.mockStatic(ApiResource.class);
    Mockito.when(ApiResource.buildRequestURL(Mockito.any())).thenReturn(TEST_URL);
  }

  @Test
  public void testSuccess() throws UizaException {
    JsonObject expectedOfCreate = new JsonObject();
    expectedOfCreate.addProperty("id", USER_ID);

    Map<String, Object> paramsOfCreate = new HashMap<>();
    paramsOfCreate.put("status", Status.ACTIVE.getVal());
    paramsOfCreate.put("username", "User Name");
    paramsOfCreate.put("email", "Email");
    paramsOfCreate.put("password", "Password");
    paramsOfCreate.put("fullname", "Fullname");
    paramsOfCreate.put("dob", "dd/MM/yyyy");
    paramsOfCreate.put("gender", Gender.MALE.getVal());
    paramsOfCreate.put("isAdmin", Role.ADMIN.getVal());

    JsonObject expectedOfRetrieve = new JsonObject();
    expectedOfRetrieve.addProperty("id", USER_ID);

    Map<String, Object> paramsOfRetrieve = new HashMap<>();
    paramsOfRetrieve.put("id", USER_ID);

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, paramsOfCreate,
        DescriptionLink.CREATE.toString())).thenReturn(expectedOfCreate);
    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, paramsOfRetrieve,
        DescriptionLink.RETRIEVE.toString())).thenReturn(expectedOfRetrieve);
    Mockito.when(ApiResource.getId(Mockito.any())).thenCallRealMethod();
    Mockito.when(ApiResource.checkResponseType(Mockito.any())).thenCallRealMethod();

    JsonObject actual = User.create(paramsOfCreate);
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

    User.create(null);
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

    User.create(null);
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

    User.create(null);
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

    User.create(null);
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

    User.create(null);
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

    User.create(null);
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

    User.create(null);
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

    User.create(null);
  }

  @Test
  public void testFailedThrowsUizaException() throws UizaException {
    UizaException e = new UizaException("", USER_ID, 300, DescriptionLink.CREATE.toString());

    Mockito.when(
        ApiResource.request(RequestMethod.POST, TEST_URL, null, DescriptionLink.CREATE.toString()))
        .thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    User.create(null);
  }
}
