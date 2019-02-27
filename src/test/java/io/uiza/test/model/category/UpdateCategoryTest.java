package io.uiza.test.model.category;

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
import io.uiza.model.Category;
import io.uiza.net.ApiResource;
import io.uiza.net.ApiResource.RequestMethod;
import io.uiza.net.util.ErrorMessage;
import io.uiza.test.TestBase;

@PowerMockIgnore("javax.net.ssl.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest(ApiResource.class)
public class UpdateCategoryTest extends TestBase {

  private Map<String, Object> params;

  @Before
  public void setUp() throws Exception {
    params = new HashMap<>();
    params.put("id", CATEGORY_ID);
    params.put("name", "Sample Video");

    PowerMockito.mockStatic(ApiResource.class);
    Mockito.when(ApiResource.buildRequestURL(Mockito.any())).thenReturn(TEST_URL);
  }

  @Test
  public void testInitParamsWhenNull() throws UizaException {
    JsonObject expectedOfUpdate = new JsonObject();
    expectedOfUpdate.addProperty("id", CATEGORY_ID);

    Map<String, Object> paramsOfUpdate = null;

    JsonObject expectedOfRetrieve = new JsonObject();
    expectedOfRetrieve.addProperty("id", CATEGORY_ID);
    expectedOfRetrieve.addProperty("name", "Name");

    Map<String, Object> paramsOfRetrieve = new HashMap<>();
    paramsOfRetrieve.put("id", CATEGORY_ID);

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, paramsOfUpdate))
        .thenReturn(expectedOfUpdate);
    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, paramsOfRetrieve))
        .thenReturn(expectedOfRetrieve);
    Mockito.when(ApiResource.checkResponseType(Mockito.any())).thenCallRealMethod();

    JsonObject actual = Category.update(CATEGORY_ID, paramsOfUpdate);
    Assert.assertEquals(expectedOfRetrieve, actual);
  }

  @Test
  public void testSuccess() throws UizaException {
    JsonObject expectedOfUpdate = new JsonObject();
    expectedOfUpdate.addProperty("id", CATEGORY_ID);

    Map<String, Object> paramsOfUpdate = new HashMap<>();
    paramsOfUpdate.put("id", CATEGORY_ID);
    paramsOfUpdate.put("name", "Name");

    JsonObject expectedOfRetrieve = new JsonObject();
    expectedOfRetrieve.addProperty("id", CATEGORY_ID);
    expectedOfRetrieve.addProperty("name", "Name");

    Map<String, Object> paramsOfRetrieve = new HashMap<>();
    paramsOfRetrieve.put("id", CATEGORY_ID);

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, paramsOfUpdate))
        .thenReturn(expectedOfUpdate);
    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, paramsOfRetrieve))
        .thenReturn(expectedOfRetrieve);
    Mockito.when(ApiResource.checkResponseType(Mockito.any())).thenCallRealMethod();

    JsonObject actual = Category.update(CATEGORY_ID, paramsOfUpdate);
    Assert.assertEquals(expectedOfRetrieve, actual);
  }

  @Test
  public void testFailedThrowsBadRequestException() throws UizaException {
    UizaException e = new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, CATEGORY_ID, 400);

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.update(CATEGORY_ID, params);
  }

  @Test
  public void testFailedThrowsUnauthorizedException() throws UizaException {
    UizaException e = new UnauthorizedException(ErrorMessage.UNAUTHORIZED_ERROR, CATEGORY_ID, 401);

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.update(CATEGORY_ID, params);
  }

  @Test
  public void testFailedThrowsNotFoundException() throws UizaException {
    UizaException e = new NotFoundException(ErrorMessage.NOT_FOUND_ERROR, CATEGORY_ID, 404);

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.update(CATEGORY_ID, params);
  }

  @Test
  public void testFailedThrowsUnprocessableException() throws UizaException {
    UizaException e =
        new UnprocessableException(ErrorMessage.UNPROCESSABLE_ERROR, CATEGORY_ID, 422);

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.update(CATEGORY_ID, params);
  }

  @Test
  public void testFailedThrowsInternalServerException() throws UizaException {
    UizaException e =
        new InternalServerException(ErrorMessage.INTERNAL_SERVER_ERROR, CATEGORY_ID, 500);

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.update(CATEGORY_ID, params);
  }

  @Test
  public void testFailedThrowsServiceUnavailableException() throws UizaException {
    UizaException e =
        new ServiceUnavailableException(ErrorMessage.SERVICE_UNAVAILABLE_ERROR, CATEGORY_ID, 503);

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.update(CATEGORY_ID, params);
  }

  @Test
  public void testFailedThrowsClientException() throws UizaException {
    UizaException e = new ClientException(ErrorMessage.CLIENT_ERROR, CATEGORY_ID, 450);

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.update(CATEGORY_ID, params);
  }

  @Test
  public void testFailedThrowsServerException() throws UizaException {
    UizaException e = new ServerException(ErrorMessage.SERVER_ERROR, CATEGORY_ID, 550);

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.update(CATEGORY_ID, params);
  }

  @Test
  public void testFailedThrowsUizaException() throws UizaException {
    UizaException e = new UizaException(CATEGORY_ID, CATEGORY_ID, 300);

    Mockito.when(ApiResource.request(RequestMethod.PUT, TEST_URL, params)).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.update(CATEGORY_ID, params);
  }
}
