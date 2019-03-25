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

import com.google.gson.JsonArray;
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
public class DeleteRelationTest extends TestBase {

  @Before
  public void setUp() throws Exception {
    PowerMockito.mockStatic(ApiResource.class);
    Mockito.when(ApiResource.buildRequestURL(Mockito.any())).thenReturn(TEST_URL);
  }

  @Test
  public void testSuccess() throws UizaException {
    JsonArray expected = new JsonArray();
    JsonObject childExpected = new JsonObject();
    childExpected.addProperty("id", RELATION_ID);
    childExpected.addProperty("entityId", ENTITY_ID);
    childExpected.addProperty("metadataIds", CATEGORY_ID);

    Map<String, Object> params = new HashMap<>();
    params.put("entityId", ENTITY_ID);
    params.put("metadataIds", new String[] {CATEGORY_ID});

    Mockito.when(ApiResource.request(RequestMethod.DELETE, TEST_URL, params,
        DescriptionLink.DELETE_RELATION.toString())).thenReturn(expected);
    Mockito.when(ApiResource.checkResponseType(Mockito.any())).thenCallRealMethod();

    JsonArray actual = Category.deleteRelation(params);
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testFailedThrowsBadRequestException() throws UizaException {
    UizaException e = new BadRequestException(ErrorMessage.BAD_REQUEST_ERROR, "", 400,
        DescriptionLink.DELETE_RELATION.toString());

    Mockito.when(ApiResource.request(RequestMethod.DELETE, TEST_URL, null,
        DescriptionLink.DELETE_RELATION.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.deleteRelation(null);
  }

  @Test
  public void testFailedThrowsUnauthorizedException() throws UizaException {
    UizaException e = new UnauthorizedException(ErrorMessage.UNAUTHORIZED_ERROR, "", 401,
        DescriptionLink.DELETE_RELATION.toString());

    Mockito.when(ApiResource.request(RequestMethod.DELETE, TEST_URL, null,
        DescriptionLink.DELETE_RELATION.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.deleteRelation(null);
  }

  @Test
  public void testFailedThrowsNotFoundException() throws UizaException {
    UizaException e = new NotFoundException(ErrorMessage.NOT_FOUND_ERROR, "", 404,
        DescriptionLink.DELETE_RELATION.toString());

    Mockito.when(ApiResource.request(RequestMethod.DELETE, TEST_URL, null,
        DescriptionLink.DELETE_RELATION.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.deleteRelation(null);
  }

  @Test
  public void testFailedThrowsUnprocessableException() throws UizaException {
    UizaException e = new UnprocessableException(ErrorMessage.UNPROCESSABLE_ERROR, "", 422,
        DescriptionLink.DELETE_RELATION.toString());

    Mockito.when(ApiResource.request(RequestMethod.DELETE, TEST_URL, null,
        DescriptionLink.DELETE_RELATION.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.deleteRelation(null);
  }

  @Test
  public void testFailedThrowsInternalServerException() throws UizaException {
    UizaException e = new InternalServerException(ErrorMessage.INTERNAL_SERVER_ERROR, "", 500,
        DescriptionLink.DELETE_RELATION.toString());

    Mockito.when(ApiResource.request(RequestMethod.DELETE, TEST_URL, null,
        DescriptionLink.DELETE_RELATION.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.deleteRelation(null);
  }

  @Test
  public void testFailedThrowsServiceUnavailableException() throws UizaException {
    UizaException e = new ServiceUnavailableException(ErrorMessage.SERVICE_UNAVAILABLE_ERROR, "",
        503, DescriptionLink.DELETE_RELATION.toString());

    Mockito.when(ApiResource.request(RequestMethod.DELETE, TEST_URL, null,
        DescriptionLink.DELETE_RELATION.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.deleteRelation(null);
  }

  @Test
  public void testFailedThrowsClientException() throws UizaException {
    UizaException e = new ClientException(ErrorMessage.CLIENT_ERROR, "", 450,
        DescriptionLink.DELETE_RELATION.toString());

    Mockito.when(ApiResource.request(RequestMethod.DELETE, TEST_URL, null,
        DescriptionLink.DELETE_RELATION.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.deleteRelation(null);
  }

  @Test
  public void testFailedThrowsServerException() throws UizaException {
    UizaException e = new ServerException(ErrorMessage.SERVER_ERROR, "", 550,
        DescriptionLink.DELETE_RELATION.toString());

    Mockito.when(ApiResource.request(RequestMethod.DELETE, TEST_URL, null,
        DescriptionLink.DELETE_RELATION.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.deleteRelation(null);
  }

  @Test
  public void testFailedThrowsUizaException() throws UizaException {
    UizaException e = new UizaException("", "", 300, DescriptionLink.DELETE_RELATION.toString());

    Mockito.when(ApiResource.request(RequestMethod.DELETE, TEST_URL, null,
        DescriptionLink.DELETE_RELATION.toString())).thenThrow(e);
    expectedException.expect(e.getClass());
    expectedException.expectMessage(e.getMessage());

    Category.deleteRelation(null);
  }
}
