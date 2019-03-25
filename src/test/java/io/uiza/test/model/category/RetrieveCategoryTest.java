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
import io.uiza.model.Category.DescriptionLink;
import io.uiza.net.ApiResource;
import io.uiza.net.ApiResource.RequestMethod;
import io.uiza.net.util.ErrorMessage;
import io.uiza.test.TestBase;

@PowerMockIgnore("javax.net.ssl.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest(ApiResource.class)
public class RetrieveCategoryTest extends TestBase {

  private Map<String, Object> params;

  @Before
  public void setUp() throws Exception {
    params = new HashMap<>();
    params.put("id", CATEGORY_ID);

    PowerMockito.mockStatic(ApiResource.class);
    Mockito.when(ApiResource.buildRequestURL(Mockito.any())).thenReturn(TEST_URL);
  }

  @Test
  public void testIdIsNullThrowsBadRequestException() throws UizaException {
    UizaException e = new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, "", 400,
        DescriptionLink.RETRIEVE.toString());

    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.retrieve(null);
  }

  @Test
  public void testIdIsEmptyThrowsBadRequestException() throws UizaException {
    UizaException e = new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, "", 400,
        DescriptionLink.RETRIEVE.toString());

    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.retrieve("");
  }

  @Test
  public void testSuccess() throws UizaException {
    JsonObject expected = new JsonObject();
    expected.addProperty("id", CATEGORY_ID);

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, params,
        DescriptionLink.RETRIEVE.toString())).thenReturn(expected);
    Mockito.when(ApiResource.checkResponseType(Mockito.any())).thenCallRealMethod();

    JsonObject actual = Category.retrieve(CATEGORY_ID);
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testFailedThrowsBadRequestException() throws UizaException {
    UizaException e = new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, CATEGORY_ID, 400,
        DescriptionLink.RETRIEVE.toString());

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, params,
        DescriptionLink.RETRIEVE.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.retrieve(CATEGORY_ID);
  }

  @Test
  public void testFailedThrowsUnauthorizedException() throws UizaException {
    UizaException e = new UnauthorizedException(ErrorMessage.UNAUTHORIZED_ERROR, CATEGORY_ID, 401,
        DescriptionLink.RETRIEVE.toString());

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, params,
        DescriptionLink.RETRIEVE.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.retrieve(CATEGORY_ID);
  }

  @Test
  public void testFailedThrowsNotFoundException() throws UizaException {
    UizaException e = new NotFoundException(ErrorMessage.NOT_FOUND_ERROR, CATEGORY_ID, 404,
        DescriptionLink.RETRIEVE.toString());

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, params,
        DescriptionLink.RETRIEVE.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.retrieve(CATEGORY_ID);
  }

  @Test
  public void testFailedThrowsUnprocessableException() throws UizaException {
    UizaException e = new UnprocessableException(ErrorMessage.UNPROCESSABLE_ERROR, CATEGORY_ID, 422,
        DescriptionLink.RETRIEVE.toString());

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, params,
        DescriptionLink.RETRIEVE.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.retrieve(CATEGORY_ID);
  }

  @Test
  public void testFailedThrowsInternalServerException() throws UizaException {
    UizaException e = new InternalServerException(ErrorMessage.INTERNAL_SERVER_ERROR, CATEGORY_ID,
        500, DescriptionLink.RETRIEVE.toString());

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, params,
        DescriptionLink.RETRIEVE.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.retrieve(CATEGORY_ID);
  }

  @Test
  public void testFailedThrowsServiceUnavailableException() throws UizaException {
    UizaException e = new ServiceUnavailableException(ErrorMessage.SERVICE_UNAVAILABLE_ERROR,
        CATEGORY_ID, 503, DescriptionLink.RETRIEVE.toString());

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, params,
        DescriptionLink.RETRIEVE.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.retrieve(CATEGORY_ID);
  }

  @Test
  public void testFailedThrowsClientException() throws UizaException {
    UizaException e = new ClientException(ErrorMessage.CLIENT_ERROR, CATEGORY_ID, 450,
        DescriptionLink.RETRIEVE.toString());

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, params,
        DescriptionLink.RETRIEVE.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.retrieve(CATEGORY_ID);
  }

  @Test
  public void testFailedThrowsServerException() throws UizaException {
    UizaException e = new ServerException(ErrorMessage.SERVER_ERROR, CATEGORY_ID, 550,
        DescriptionLink.RETRIEVE.toString());

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, params,
        DescriptionLink.RETRIEVE.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.retrieve(CATEGORY_ID);
  }

  @Test
  public void testFailedThrowsUizaException() throws UizaException {
    UizaException e = new UizaException("", CATEGORY_ID, 300, DescriptionLink.RETRIEVE.toString());

    Mockito.when(ApiResource.request(RequestMethod.GET, TEST_URL, params,
        DescriptionLink.RETRIEVE.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.retrieve(CATEGORY_ID);
  }
}
