package uiza;

import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.*;
import io.uiza.model.Entity.*;

public class Test {

  public static void main(String[] args) {
    Uiza.authorization = "AUTHORIZATION_KEY";

    try {
      JsonObject response = Live.getRegions();
    } catch (UizaException e) {
      System.out.println("Status is: " + e.getStatusCode());
      System.out.println("Message is: " + e.getMessage());
      System.out.println("Description link is: " + e.getDescriptionLink());
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}