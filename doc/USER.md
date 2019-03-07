# User

## Create a user

Create a user account for workspace.
See details [here](https://docs.uiza.io/#create-an-user).

```java
import io.uiza.model.User;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

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
  JsonObject user = User.create(params);
  System.out.println(user.get("id"));
} catch (UizaException e) {
  System.out.println("Status is: " + e.getStatusCode());
  System.out.println("Message is: " + e.getMessage());
  System.out.println("Description link is: " + e.getDescriptionLink());
} catch (Exception e) {

}
```

Example Response

```java
{
  "id": "37d6706e-be91-463e-b3b3-b69451dd4752",
  "isAdmin": 1,
  "username": "user_test",
  "email": "user_test@uiza.io",
  "updatedAt": "2018-06-22T18:05:47.000Z",
  "createdAt": "2018-06-22T18:05:47.000Z"
}
```

## Retrieve a user

Retrieves the details of an existing user.
You need only supply the unique userId that was returned upon user creation.
See details [here](https://docs.uiza.io/#retrieve-an-user).

```java
import io.uiza.model.User;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

try {
  JsonObject user = User.retrieve("<user-id>");
  System.out.println(user.get("username"));
} catch (UizaException e) {
  System.out.println("Status is: " + e.getStatusCode());
  System.out.println("Message is: " + e.getMessage());
  System.out.println("Description link is: " + e.getDescriptionLink());
} catch (Exception e) {

}
```

Example Response

```java
{
  "id": "37d6706e-be91-463e-b3b3-b69451dd4752",
  "isAdmin": 1,
  "username": "user_test",
  "email": "user_test@uiza.io",
  "updatedAt": "2018-06-22T18:05:47.000Z",
  "createdAt": "2018-06-22T18:05:47.000Z"
}
```

## List all users

Returns a list of your users.
The users are returned sorted by creation date, with the most recent user appearing first.

If you use Admin token, you will get all the users.
If you use User token, you can only get the information of that user.
See details [here](https://docs.uiza.io/#list-all-users).

```java
import io.uiza.model.User;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

try {
  JsonArray users = User.list();
  JsonObject user = users.get(0).getAsJsonObject();
  System.out.println(user.get("username"));
} catch (UizaException e) {
  System.out.println("Status is: " + e.getStatusCode());
  System.out.println("Message is: " + e.getMessage());
  System.out.println("Description link is: " + e.getDescriptionLink());
} catch (Exception e) {

}
```

Example Response

```java
[
  {
    "id": "1a95f752-19e0-4a2e-9951-6d1fc0adbeaf",
    "isAdmin": 0,
    "username": "user_test",
    "email": "user_test@uiza.io",
    "updatedAt": "2018-06-22T02:31:14.000Z",
    "createdAt": "2018-06-22T02:31:14.000Z"
  },
  {
    "id": "95c1229a-73e6-4ef7-98eb-e64a765c32d5",
    "isAdmin": 0,
    "username": "user_admin",
    "email": "user_admin@uiza.io",
    "updatedAt": "2018-06-22T00:00:00.000Z",
    "createdAt": "2018-06-22T02:32:29.000Z"
  }
]
```

## Update a user

Updates the specified user by setting the values of the parameters passed.
Any parameters not provided will be left unchanged.
See details [here](https://docs.uiza.io/#update-an-user).

```java
import io.uiza.model.User;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

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
  JsonObject user = User.update("<user-id>", params);
  System.out.println(user.get("email"));
} catch (UizaException e) {
  System.out.println("Status is: " + e.getStatusCode());
  System.out.println("Message is: " + e.getMessage());
  System.out.println("Description link is: " + e.getDescriptionLink());
} catch (Exception e) {

}
```

Example Response

```java
{
  "id": "37d6706e-be91-463e-b3b3-b69451dd4752",
  "isAdmin": 1,
  "username": "user_test",
  "email": "user_test@uiza.io",
  "updatedAt": "2018-06-22T18:05:47.000Z",
  "createdAt": "2018-06-22T18:05:47.000Z"
}
```

## Delete a user

Permanently deletes a user.
It cannot be undone.
Also immediately cancels all token & information of this user.
See details [here](https://docs.uiza.io/#delete-an-user).

```java
import io.uiza.model.User;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

try {
  JsonObject user = User.delete("<user-id>");
  System.out.println(user.get("id"));
} catch (UizaException e) {
  System.out.println("Status is: " + e.getStatusCode());
  System.out.println("Message is: " + e.getMessage());
  System.out.println("Description link is: " + e.getDescriptionLink());
} catch (Exception e) {

}
```

Example Response

```java
{
  "id": "37d6706e-be91-463e-b3b3-b69451dd4752"
}
```

## Update password

Update password allows Admin or User update their current password.
See details [here](https://docs.uiza.io/#update-password).

```java
import io.uiza.model.User;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

Map<String, Object> params = new HashMap<>();
params.put("oldPassword", "FMpsr<4[dGPu?B#u");
params.put("newPassword", "S57Eb{:aMZhW=)G$");

try {
  JsonObject user = User.changePassword("<user-id>", params);
  System.out.println(user);
} catch (UizaException e) {
  System.out.println("Status is: " + e.getStatusCode());
  System.out.println("Message is: " + e.getMessage());
  System.out.println("Description link is: " + e.getDescriptionLink());
} catch (Exception e) {

}
```

Example Response

```java
{
  "result":"ok"
}
```

## Log Out

Log out a user.
After logged out, token will be removed.
See details [here](https://docs.uiza.io/#log-out).

```java
import io.uiza.model.User;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

try {
  JsonObject user = User.logout();
  System.out.println(user);
} catch (UizaException e) {
  System.out.println("Status is: " + e.getStatusCode());
  System.out.println("Message is: " + e.getMessage());
  System.out.println("Description link is: " + e.getDescriptionLink());
} catch (Exception e) {

}
```

Example Response

```java
{
  "message": "Logout success"
}
```
