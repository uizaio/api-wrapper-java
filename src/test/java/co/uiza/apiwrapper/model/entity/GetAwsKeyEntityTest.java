package co.uiza.apiwrapper.model.entity;

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
import co.uiza.apiwrapper.TestBase;
import co.uiza.apiwrapper.exception.BadRequestException;
import co.uiza.apiwrapper.exception.ClientException;
import co.uiza.apiwrapper.exception.InternalServerException;
import co.uiza.apiwrapper.exception.NotFoundException;
import co.uiza.apiwrapper.exception.ServerException;
import co.uiza.apiwrapper.exception.ServiceUnavailableException;
import co.uiza.apiwrapper.exception.UizaException;
import co.uiza.apiwrapper.exception.UnauthorizedException;
import co.uiza.apiwrapper.exception.UnprocessableException;
import co.uiza.apiwrapper.model.Entity;
import co.uiza.apiwrapper.net.ApiResource;
import co.uiza.apiwrapper.net.ApiResource.RequestMethod;
import co.uiza.apiwrapper.net.util.ErrorMessage;

@PowerMockIgnore("javax.net.ssl.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest(ApiResource.class)
public class GetAwsKeyEntityTest extends TestBase {

  @Before
  public void setUp() throws Exception {
    PowerMockito.mockStatic(ApiResource.class);
    Mockito.when(ApiResource.buildRequestURL(Mockito.any())).thenReturn(TEST_URL);
  }

  @Test
  public void testSuccess() throws UizaException {
    JsonObject expected = new JsonObject();
    expected.addProperty("temp_expire_at", "1533658598");
    expected.addProperty("region_name", "ap-southeast-1");

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, null)).thenReturn(expected);
    Mockito.when(ApiResource.checkResponseType(Mockito.any())).thenCallRealMethod();

    JsonObject actual = Entity.getAwsKeyEntity();
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testFailedThrowsBadRequestException() throws UizaException {
    UizaException e = new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, "", 400);

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, null)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Entity.getAwsKeyEntity();
  }

  @Test
  public void testFailedThrowsUnauthorizedException() throws UizaException {
    UizaException e = new UnauthorizedException(ErrorMessage.UNAUTHORIZED_ERROR, "", 401);

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, null)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Entity.getAwsKeyEntity();
  }

  @Test
  public void testFailedThrowsNotFoundException() throws UizaException {
    UizaException e = new NotFoundException(ErrorMessage.NOT_FOUND_ERROR, "", 404);

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, null)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Entity.getAwsKeyEntity();
  }

  @Test
  public void testFailedThrowsUnprocessableException() throws UizaException {
    UizaException e = new UnprocessableException(ErrorMessage.UNPROCESSABLE_ERROR, "", 422);

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, null)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Entity.getAwsKeyEntity();
  }

  @Test
  public void testFailedThrowsInternalServerException() throws UizaException {
    UizaException e = new InternalServerException(ErrorMessage.INTERNAL_SERVER_ERROR, "", 500);

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, null)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Entity.getAwsKeyEntity();
  }

  @Test
  public void testFailedThrowsServiceUnavailableException() throws UizaException {
    UizaException e =
        new ServiceUnavailableException(ErrorMessage.SERVICE_UNAVAILABLE_ERROR, "", 503);

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, null)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Entity.getAwsKeyEntity();
  }

  @Test
  public void testFailedThrowsClientException() throws UizaException {
    UizaException e = new ClientException(ErrorMessage.CLIENT_ERROR, "", 450);

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, null)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Entity.getAwsKeyEntity();
  }

  @Test
  public void testFailedThrowsServerException() throws UizaException {
    UizaException e = new ServerException(ErrorMessage.SERVER_ERROR, "", 550);

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, null)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Entity.getAwsKeyEntity();
  }

  @Test
  public void testFailedThrowsUizaException() throws UizaException {
    UizaException e = new UizaException("", "", 300);

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, null)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Entity.getAwsKeyEntity();
  }
}
