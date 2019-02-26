## Create a callback
Setup a callback to your server when an entity is completed for upload or public.
See details [here](https://docs.uiza.io/#create-a-callback).

```java
import io.uiza.model.Callback;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

Map<String, Object> params = new HashMap<>();
params.put("url", "<your-server-callback>");
params.put("method", Method.POST);

try {
  JsonObject callback = Callback.create(params);
  System.out.println(callback.get("id"));
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
See details [here](https://docs.uiza.io/#retrieve-a-callback).

```java
import io.uiza.model.Callback;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

try {
  JsonObject callback = Callback.retrieve("<callback-id>");
  System.out.println(callback.get("url"));
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
See details [here](https://docs.uiza.io/#update-a-callback).

```java
import io.uiza.model.Callback;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

Map<String, Object> params = new HashMap<>();
params.put("url", "<your-server-callback>");
params.put("method", Method.POST);

try {
  JsonObject callback = Callback.update("<callback-id>", params);
  System.out.println(callback.get("name"));
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
See details [here](https://docs.uiza.io/#delete-a-callback).

```java
import io.uiza.model.Callback;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

try {
  JsonObject callback = Callback.delete("<callback-id>");
  System.out.println(callback.get("id"));
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
  "id": "c54f115f-87b4-420c-9e52-e8dffe32e022"
}
```
