package io.uiza.test;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class TestBase {
  protected final String TEST_URL = "uiza.co/test";
  protected final String ENTITY_ID = "16ab25d3-fd0f-4568-8aa0-0339bbfd674f";
  protected final String STORAGE_ID = "013130d7-abba-466b-bc47-c86c628a305e";
  protected final String CATEGORY_ID = "32e8a1f4-e3b6-4369-a30d-60c6715896d1";
  protected final String RELATION_ID = "737a3683-d5ec-4440-9021-721135141ecb";
  protected final String LIVE_ID = "7fc80748-e3f9-4ff7-bb8a-cf37e8918239";
  protected final String CALLBACK_ID = "8b83886e-9cc3-4eab-9258-ebb16c0c73de";
  protected final String USER_ID = "37d6706e-be91-463e-b3b3-b69451dd4752";

  @Rule
  protected ExpectedException expectedException = ExpectedException.none();
}
