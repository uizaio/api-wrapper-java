# User

## Retrieve a user

Retrieves the details of an existing user.
You need only supply the unique userId that was returned upon user creation.
See details [here](http://dev-ap-southeast-1-api.uizadev.io/docs/#api-User-get_userInfo).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.User;
import io.uiza.model.User.*;

public class Main {

  public static void main(String[] args) {
    Uiza.authorization = "your-authorization";
    Uiza.appId = "your-app-id";

    try {
      JsonObject response = User.retrieve("<user-id>");
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
  "id": "00e8d66e-09e0-4d61-9b98-e66c96620110",
  "email": "user_test@gmail.com",
  "dob": null,
  "name": null,
  "status": 1,
  "avatar": null,
  "createdAt": "2019-03-14T12:43:00.000Z",
  "updatedAt": "2019-03-14T12:43:00.000Z"
}
```

## List all users

Returns a list of your users.
The users are returned sorted by creation date, with the most recent user appearing first.

If you use Admin token, you will get all the users.
If you use User token, you can only get the information of that user.
See details [here](http://dev-ap-southeast-1-api.uizadev.io/docs/#api-User-get_userInfo).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.User;
import io.uiza.model.User.*;

public class Main {

  public static void main(String[] args) {
    Uiza.authorization = "your-authorization";
    Uiza.appId = "your-app-id";

    try {
      JsonArray response = User.list();
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
[
  {
    "id": "00e8d66e-09e0-4d61-9b98-e66c96620110",
    "email": "user_test@gmail.com",
    "dob": null,
    "name": null,
    "status": 1,
    "avatar": null,
    "createdAt": "2019-03-14T12:43:00.000Z",
    "updatedAt": "2019-03-14T12:43:00.000Z"
  },
  {
    "id": "a358fb5c-2ba7-4932-a3df-be6e65e8f301",
    "email": "user_test@gmail.com",
    "dob": null,
    "name": null,
    "status": 1,
    "avatar": null,
    "createdAt": "2019-03-14T11:26:06.000Z",
    "updatedAt": "2019-03-14T11:26:06.000Z"
  }
]
```

## Update a user

Updates the specified user by setting the values of the parameters passed.
Any parameters not provided will be left unchanged.
See details [here](http://dev-ap-southeast-1-api.uizadev.io/docs/#api-User-update_userInfo).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.User;
import io.uiza.model.User.*;

public class Main {

  public static void main(String[] args) {
    Uiza.authorization = "your-authorization";
    Uiza.appId = "your-app-id";

    Map<String, Object> params = new HashMap<>();
    params.put("status", Status.ACTIVE.getVal());
    params.put("username", "user_test");
    params.put("email", "user_test@uiza.io");
    params.put("fullname", "User Test");
    params.put("avatar", "https://exemple.com/avatar.jpeg");
    params.put("dob", "05/15/2018");
    params.put("gender", Gender.MALE.getVal());
    params.put("password", "FMpsr<4[dGPu?B#u");
    params.put("isAdmin", Role.ADMIN.getVal());

    try {
      JsonObject response = User.update("<user-id>", params);
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
  "id": "a358fb5c-2ba7-4932-a3df-be6e65e8f301",
  "email": "user_test@gmail.com",
  "dob": "2019-02-27",
  "name": "user_test",
  "status": 1,
  "avatar": "https://example.avatar.com/user_test.png",
  "createdAt": "2019-03-14T11:26:06.000Z",
  "updatedAt": "2019-03-14T11:26:06.000Z"
}
```

## Update password

Update password allows Admin or User update their current password.
See details [here](http://dev-ap-southeast-1-api.uizadev.io/docs/#api-User-changePassword).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.User;
import io.uiza.model.User.*;

public class Main {

  public static void main(String[] args) {
    Uiza.authorization = "your-authorization";
    Uiza.appId = "your-app-id";

    Map<String, Object> params = new HashMap<>();
    params.put("oldPassword", "FMpsr<4[dGPu?B#u");
    params.put("newPassword", "S57Eb{:aMZhW=)G$");

    try {
      JsonObject response = User.changePassword("<user-id>", params);
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
  "id": "5167cf93-6fcd-454d-80a7-92f1b2d81fd4",
  "email": "user_test@gmail.com",
  "dob": "2019-02-27T00:00:00.000Z",
  "name": "user_test",
  "status": 1,
  "avatar": "https://example.avatar.com/user_test.png",
  "createdAt": "2019-02-27T07:46:28.000Z",
  "updatedAt": "2019-03-19T02:29:10.000Z"
}
```

## Log Out

Log out a user.
After logged out, token will be removed.
See details [here](http://dev-ap-southeast-1-api.uizadev.io/docs/#api-User-Logout).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.User;
import io.uiza.model.User.*;

public class Main {

  public static void main(String[] args) {
    Uiza.authorization = "your-authorization";
    Uiza.appId = "your-app-id";

    try {
      JsonObject response = User.logout();
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
  "message": "success"
}
```
