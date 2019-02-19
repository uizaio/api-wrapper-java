package co.uiza.apiwrapper;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class TestBase {
  protected final String TEST_URL = "uiza.co/test";
  protected final String ENTITY_ID = "16ab25d3-fd0f-4568-8aa0-0339bbfd674f";
  protected final String STORAGE_ID = "013130d7-abba-466b-bc47-c86c628a305e";
  protected final String CATEGORY_ID = "32e8a1f4-e3b6-4369-a30d-60c6715896d1";

  @Rule
  protected ExpectedException expectedException = ExpectedException.none();
}
