package co.uiza.apiwrapper.model.storage;

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
import co.uiza.apiwrapper.model.Storage;
import co.uiza.apiwrapper.net.ApiResource;
import co.uiza.apiwrapper.net.ApiResource.RequestMethod;
import co.uiza.apiwrapper.net.util.ErrorMessage;

@PowerMockIgnore("javax.net.ssl.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest(ApiResource.class)
public class AddStorageTest extends TestBase {

  @Before
  public void setUp() throws Exception {
    PowerMockito.mockStatic(ApiResource.class);
    Mockito.when(ApiResource.buildRequestURL(Mockito.any())).thenReturn(TEST_URL);
  }

  @Test
  public void testCreateFtpStorageSuccess() throws UizaException {
    JsonObject expectedOfCreate = new JsonObject();
    expectedOfCreate.addProperty("id", STORAGE_ID);

    Map<String, Object> paramsOfCreate = new HashMap<>();
    paramsOfCreate.put("name", "Name");
    paramsOfCreate.put("host", "Host");
    paramsOfCreate.put("port", "Port");
    paramsOfCreate.put("type", "Type");

    JsonObject expectedOfRetrieve = new JsonObject();
    expectedOfRetrieve.addProperty("id", STORAGE_ID);
    expectedOfRetrieve.addProperty("name", "Name");
    expectedOfRetrieve.addProperty("host", "Host");
    expectedOfRetrieve.addProperty("port", "Port");
    expectedOfRetrieve.addProperty("type", "Type");

    Map<String, Object> paramsOfRetrieve = new HashMap<>();
    paramsOfRetrieve.put("id", STORAGE_ID);

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, paramsOfCreate))
        .thenReturn(expectedOfCreate);
    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, paramsOfRetrieve))
        .thenReturn(expectedOfRetrieve);
    Mockito.when(ApiResource.getId(Mockito.any())).thenCallRealMethod();
    Mockito.when(ApiResource.checkResponseType(Mockito.any())).thenCallRealMethod();

    JsonObject actual = Storage.createFtpStorage(paramsOfCreate);
    Assert.assertEquals(expectedOfRetrieve, actual);
  }

  @Test
  public void testCreateAwsS3StorageSuccess() throws UizaException {
    JsonObject expectedOfCreate = new JsonObject();
    expectedOfCreate.addProperty("id", STORAGE_ID);

    Map<String, Object> paramsOfCreate = new HashMap<>();
    paramsOfCreate.put("name", "Name");
    paramsOfCreate.put("host", "Host");
    paramsOfCreate.put("port", "Port");
    paramsOfCreate.put("type", "ftp");

    JsonObject expectedOfRetrieve = new JsonObject();
    expectedOfRetrieve.addProperty("id", STORAGE_ID);
    expectedOfRetrieve.addProperty("name", "Name");
    expectedOfRetrieve.addProperty("host", "Host");
    expectedOfRetrieve.addProperty("port", "Port");
    expectedOfRetrieve.addProperty("type", "ftp");

    Map<String, Object> paramsOfRetrieve = new HashMap<>();
    paramsOfRetrieve.put("id", STORAGE_ID);

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, paramsOfCreate))
        .thenReturn(expectedOfCreate);
    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, paramsOfRetrieve))
        .thenReturn(expectedOfRetrieve);
    Mockito.when(ApiResource.getId(Mockito.any())).thenCallRealMethod();
    Mockito.when(ApiResource.checkResponseType(Mockito.any())).thenCallRealMethod();

    JsonObject actual = Storage.createFtpStorage(paramsOfCreate);
    Assert.assertEquals(expectedOfRetrieve, actual);
  }

  @Test
  public void testFailedThrowsBadRequestException() throws UizaException {
    UizaException e = new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, "", 400);

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, null)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Storage.createFtpStorage(null);
  }

  @Test
  public void testFailedThrowsUnauthorizedException() throws UizaException {
    UizaException e = new UnauthorizedException(ErrorMessage.UNAUTHORIZED_ERROR, "", 401);

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, null)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Storage.createFtpStorage(null);
  }

  @Test
  public void testFailedThrowsNotFoundException() throws UizaException {
    UizaException e = new NotFoundException(ErrorMessage.NOT_FOUND_ERROR, "", 404);

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, null)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Storage.createFtpStorage(null);
  }

  @Test
  public void testFailedThrowsUnprocessableException() throws UizaException {
    UizaException e = new UnprocessableException(ErrorMessage.UNPROCESSABLE_ERROR, "", 422);

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, null)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Storage.createFtpStorage(null);
  }

  @Test
  public void testFailedThrowsInternalServerException() throws UizaException {
    UizaException e = new InternalServerException(ErrorMessage.INTERNAL_SERVER_ERROR, "", 500);

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, null)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Storage.createFtpStorage(null);
  }

  @Test
  public void testFailedThrowsServiceUnavailableException() throws UizaException {
    UizaException e =
        new ServiceUnavailableException(ErrorMessage.SERVICE_UNAVAILABLE_ERROR, "", 503);

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, null)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Storage.createFtpStorage(null);
  }

  @Test
  public void testFailedThrowsClientException() throws UizaException {
    UizaException e = new ClientException(ErrorMessage.CLIENT_ERROR, "", 450);

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, null)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Storage.createFtpStorage(null);
  }

  @Test
  public void testFailedThrowsServerException() throws UizaException {
    UizaException e = new ServerException(ErrorMessage.SERVER_ERROR, "", 550);

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, null)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Storage.createFtpStorage(null);
  }

  @Test
  public void testFailedThrowsUizaException() throws UizaException {
    UizaException e = new UizaException("", "", 300);

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, null)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Storage.createFtpStorage(null);
  }
}
