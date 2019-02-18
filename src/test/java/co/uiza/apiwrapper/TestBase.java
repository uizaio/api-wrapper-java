package co.uiza.apiwrapper;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class TestBase {
  protected final String TEST_URL = "uiza.co/test";
  protected final String ENTITY_ID = "16ab25d3-fd0f-4568-8aa0-0339bbfd674f";

  @Rule
  protected ExpectedException expectedException = ExpectedException.none();
}
