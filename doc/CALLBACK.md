# Callback

## Create a callback

Setup a callback to your server when an entity is completed for upload or public.
See details [here](https://docs.uiza.io/v4/#create-a-callback).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Callback;
import io.uiza.model.Callback.*;

public class Main {

  public static void main(String[] args) {
    Uiza.appId = "d6342a7b4a6c40d2b851a54a4442ea83";
    Uiza.authorization = "uap-d6342a7b4a6c40d2b851a54a4442ea83-f3c977b7";

    JsonObject jsonData = new JsonObject();
    jsonData.addProperty("data", "Example json data");

    JsonObject headersData = new JsonObject();
    headersData.addProperty("data", "Example headers data");

    Map<String, Object> params = new HashMap<>();
    params.put("url", "https://callback-url.uiza.co");
    params.put("method", Method.POST);
    params.put("jsonData", jsonData);
    params.put("headersData", headersData);

    try {
      JsonObject response = Callback.create(params);
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
```

Example Response

```java
{
  "id": "0a6bf245-1cce-494f-a193-b5a44aa05558",
  "url": "https://callback-url.uiza.co",
  "headersData": null,
  "jsonData": {
    "text": "example callback"
  },
  "method": "POST",
  "status": 1,
  "createdAt": "2018-06-23T01:27:08.000Z",
  "updatedAt": "2018-06-23T01:27:08.000Z"
}
```

## Retrieve a callback

Retrieves the details of an existing callback.
See details [here](https://docs.uiza.io/v4/#retrieve-a-callback).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Callback;
import io.uiza.model.Callback.*;

public class Main {

  public static void main(String[] args) {
    Uiza.appId = "d6342a7b4a6c40d2b851a54a4442ea83";
    Uiza.authorization = "uap-d6342a7b4a6c40d2b851a54a4442ea83-f3c977b7";

    try {
      JsonObject response = Callback.retrieve("56422bdf-95c8-42c7-8c52-1be99e732afa");
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
```

Example Response

```java
{
  "id": "0a6bf245-1cce-494f-a193-b5a44aa05558",
  "url": "https://callback-url.uiza.co",
  "headersData": null,
  "jsonData": {
    "text": "example callback"
  },
  "method": "POST",
  "status": 1,
  "createdAt": "2018-06-23T01:27:08.000Z",
  "updatedAt": "2018-06-23T01:27:08.000Z"
}
```

## Update a callback

Setup a callback to your server when an entity is completed for upload or public.
See details [here](https://docs.uiza.io/v4/#update-a-callback).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Callback;
import io.uiza.model.Callback.*;

public class Main {

  public static void main(String[] args) {
    Uiza.appId = "d6342a7b4a6c40d2b851a54a4442ea83";
    Uiza.authorization = "uap-d6342a7b4a6c40d2b851a54a4442ea83-f3c977b7";

    JsonObject jsonData = new JsonObject();
    jsonData.addProperty("data", "Example json data updated");

    JsonObject headersData = new JsonObject();
    headersData.addProperty("data", "Example headers data updated");

    Map<String, Object> params = new HashMap<>();
    params.put("url", "https://callback-url-update.uiza.co");
    params.put("method", Method.POST);
    params.put("jsonData", jsonData);
    params.put("headersData", headersData);

    try {
      JsonObject response = Callback.update("56422bdf-95c8-42c7-8c52-1be99e732afa", params);
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
```

Example Response

```java
{
  "id": "0a6bf245-1cce-494f-a193-b5a44aa05558",
  "url": "https://callback-url.uiza.co",
  "headersData": null,
  "jsonData": {
    "text": "example callback"
  },
  "method": "POST",
  "status": 1,
  "createdAt": "2018-06-23T01:27:08.000Z",
  "updatedAt": "2018-06-23T01:27:08.000Z"
}
```

## Delete a callback

Delete an existing callback.
See details [here](https://docs.uiza.io/v4/#delete-a-callback).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Callback;
import io.uiza.model.Callback.*;

public class Main {

  public static void main(String[] args) {
    Uiza.appId = "d6342a7b4a6c40d2b851a54a4442ea83";
    Uiza.authorization = "uap-d6342a7b4a6c40d2b851a54a4442ea83-f3c977b7";

    try {
      JsonObject response = Callback.delete("14a27ff9-869a-4969-a6d9-34220f01e48c");
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
```

Example Response

```java
{
  "id": "c54f115f-87b4-420c-9e52-e8dffe32e022"
}
```
