package io.uiza.test.model.entity;

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
import io.uiza.model.Entity;
import io.uiza.model.Entity.DescriptionLink;
import io.uiza.net.ApiResource;
import io.uiza.net.ApiResource.RequestMethod;
import io.uiza.net.util.ErrorMessage;
import io.uiza.test.TestBase;

@PowerMockIgnore("javax.net.ssl.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest(ApiResource.class)
public class PublishEntityTest extends TestBase {

  private Map<String, Object> params;

  @Before
  public void setUp() throws Exception {
    params = new HashMap<>();
    params.put("id", ENTITY_ID);

    PowerMockito.mockStatic(ApiResource.class);
    Mockito.when(ApiResource.buildRequestURL(Mockito.any())).thenReturn(TEST_URL);
  }

  @Test
  public void testSuccess() throws UizaException {
    JsonObject expected = new JsonObject();
    expected.addProperty("message",
        "Your entity started publish, check process status with this entity ID");
    expected.addProperty("entityId", ENTITY_ID);

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, params,
        DescriptionLink.PUBLISH.toString())).thenReturn(expected);
    Mockito.when(ApiResource.checkResponseType(Mockito.any())).thenCallRealMethod();

    JsonObject actual = Entity.publish(ENTITY_ID);
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testFailedThrowsBadRequestException() throws UizaException {
    UizaException e = new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, ENTITY_ID, 400,
        DescriptionLink.PUBLISH.toString());

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, params,
        DescriptionLink.PUBLISH.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Entity.publish(ENTITY_ID);
  }

  @Test
  public void testFailedThrowsUnauthorizedException() throws UizaException {
    UizaException e = new UnauthorizedException(ErrorMessage.UNAUTHORIZED_ERROR, ENTITY_ID, 401,
        DescriptionLink.PUBLISH.toString());

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, params,
        DescriptionLink.PUBLISH.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Entity.publish(ENTITY_ID);
  }

  @Test
  public void testFailedThrowsNotFoundException() throws UizaException {
    UizaException e = new NotFoundException(ErrorMessage.NOT_FOUND_ERROR, ENTITY_ID, 404,
        DescriptionLink.PUBLISH.toString());

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, params,
        DescriptionLink.PUBLISH.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Entity.publish(ENTITY_ID);
  }

  @Test
  public void testFailedThrowsUnprocessableException() throws UizaException {
    UizaException e = new UnprocessableException(ErrorMessage.UNPROCESSABLE_ERROR, ENTITY_ID, 422,
        DescriptionLink.PUBLISH.toString());

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, params,
        DescriptionLink.PUBLISH.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Entity.publish(ENTITY_ID);
  }

  @Test
  public void testFailedThrowsInternalServerException() throws UizaException {
    UizaException e = new InternalServerException(ErrorMessage.INTERNAL_SERVER_ERROR, ENTITY_ID,
        500, DescriptionLink.PUBLISH.toString());

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, params,
        DescriptionLink.PUBLISH.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Entity.publish(ENTITY_ID);
  }

  @Test
  public void testFailedThrowsServiceUnavailableException() throws UizaException {
    UizaException e = new ServiceUnavailableException(ErrorMessage.SERVICE_UNAVAILABLE_ERROR,
        ENTITY_ID, 503, DescriptionLink.PUBLISH.toString());

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, params,
        DescriptionLink.PUBLISH.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Entity.publish(ENTITY_ID);
  }

  @Test
  public void testFailedThrowsClientException() throws UizaException {
    UizaException e = new ClientException(ErrorMessage.CLIENT_ERROR, ENTITY_ID, 450,
        DescriptionLink.PUBLISH.toString());

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, params,
        DescriptionLink.PUBLISH.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Entity.publish(ENTITY_ID);
  }

  @Test
  public void testFailedThrowsServerException() throws UizaException {
    UizaException e = new ServerException(ErrorMessage.SERVER_ERROR, ENTITY_ID, 550,
        DescriptionLink.PUBLISH.toString());

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, params,
        DescriptionLink.PUBLISH.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Entity.publish(ENTITY_ID);
  }

  @Test
  public void testFailedThrowsUizaException() throws UizaException {
    UizaException e = new UizaException("", ENTITY_ID, 300, DescriptionLink.PUBLISH.toString());

    Mockito.when(ApiResource.request(RequestMethod.POST, TEST_URL, params,
        DescriptionLink.PUBLISH.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Entity.publish(ENTITY_ID);
  }
}
