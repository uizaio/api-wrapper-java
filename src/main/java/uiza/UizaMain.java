package uiza;

import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.*;
import io.uiza.model.Entity.*;

public class UizaMain {

  public static void main(String[] args) {
    Uiza.authorization = "uap-212f8ac7dcc7471c936babf43a1a252e-57078be1";

    try {
      JsonArray response = Entity.list();
      System.out.println(response);
    } catch (UizaException e) {
      System.out.println("Status is: " + e.getStatusCode());
      System.out.println("Message is: " + e.getMessage());
      System.out.println("Description link is: " + e.getDescriptionLink());
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
