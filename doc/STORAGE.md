## Add a storage
You can add your storage (`FTP`, `AWS S3`) with UIZA.
After synced, you can select your content easier from your storage to [create entity](https://docs.uiza.io/#create-entity).
See details [here](https://docs.uiza.io/#add-a-storage).

```java
import static co.uiza.apiwrapper.model.Storage.addStorage;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

Map<String, Object> params = new HashMap<>();
params.put("name", "FTP Uiza");
params.put("host", "ftp-example.uiza.io");
params.put("port", "21");
params.put("type", "ftp");

try {
  JsonObject storage = addStorage(params);
  System.out.println(storage.get("name"));
} catch (UizaException e) {
  System.out.println("Status is: " + e.getCode());
  System.out.println("Message is: " + e.getMessage());
  System.out.println("Description link is: " + e.getDescriptionLink());
} catch (Exception e) {

}
```

Example Response
```java
{
  "id": "cd003123-7ec9-4f3a-9d7c-f2de93e83e49",
  "name": "FTP Uiza",
  "description": "FTP of Uiza, use for transcode",
  "storageType": "ftp",
  "usageType": "input",
  "bucket": null,
  "prefix": null,
  "host": "ftp-exemple.uiza.io",
  "awsAccessKey": null,
  "awsSecretKey": null,
  "username": "uiza",
  "password": "=5;9x@LPsd+w7qW",
  "region": null,
  "port": 21,
  "createdAt": "2018-06-19T03:01:56.000Z",
  "updatedAt": "2018-06-19T03:01:56.000Z"
}
```

## Retrieve a storage
Get information of your added storage (FTP or AWS S3).
See details [here](https://docs.uiza.io/#retrieve-a-storage).

```java
import static co.uiza.apiwrapper.model.Storage.retrieveStorage;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

try {
  JsonObject storage = retrieveStorage("<storage-id>");
  System.out.println(storage.get("name"));
} catch (UizaException e) {
  System.out.println("Status is: " + e.getCode());
  System.out.println("Message is: " + e.getMessage());
  System.out.println("Description link is: " + e.getDescriptionLink());
} catch (Exception e) {

}
```

Example Response
```java
{
  "id": "cd003123-7ec9-4f3a-9d7c-f2de93e83e49",
  "name": "FTP Uiza",
  "description": "FTP of Uiza, use for transcode",
  "storageType": "ftp",
  "usageType": "input",
  "bucket": null,
  "prefix": null,
  "host": "ftp-exemple.uiza.io",
  "awsAccessKey": null,
  "awsSecretKey": null,
  "username": "uiza",
  "password": "=5;9x@LPsd+w7qW",
  "region": null,
  "port": 21,
  "createdAt": "2018-06-19T03:01:56.000Z",
  "updatedAt": "2018-06-19T03:01:56.000Z"
}
```
## Update storage
Update storage's information.
See details [here](https://docs.uiza.io/#update-storage).

```java
import static co.uiza.apiwrapper.model.Storage.updateStorage;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

Map<String, Object> params = new HashMap<>();
params.put("name", "FTP Uiza");
params.put("host", "ftp-example.uiza.io");
params.put("port", "21");
params.put("type", "ftp");

try {
  JsonObject storage = udpateStorage("<storage-id>", params);
  System.out.println(storage.get("name"));
} catch (UizaException e) {
  System.out.println("Status is: " + e.getCode());
  System.out.println("Message is: " + e.getMessage());
  System.out.println("Description link is: " + e.getDescriptionLink());
} catch (Exception e) {

}
```

Example Response
```java
{
  "id": "cd003123-7ec9-4f3a-9d7c-f2de93e83e49",
  "name": "FTP Uiza",
  "description": "FTP of Uiza, use for transcode",
  "storageType": "ftp",
  "usageType": "input",
  "bucket": null,
  "prefix": null,
  "host": "ftp-exemple.uiza.io",
  "awsAccessKey": null,
  "awsSecretKey": null,
  "username": "uiza",
  "password": "=5;9x@LPsd+w7qW",
  "region": null,
  "port": 21,
  "createdAt": "2018-06-19T03:01:56.000Z",
  "updatedAt": "2018-06-19T03:01:56.000Z"
}
```

## Remove storage
Remove storage that added to Uiza.
See details [here](https://docs.uiza.io/#remove-storage).

```java
import static co.uiza.apiwrapper.model.Storage.removeStorage;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

try {
  JsonObject storage = removeStorage("<storage-id>");
  System.out.println(storage.get("id"));
} catch (UizaException e) {
  System.out.println("Status is: " + e.getCode());
  System.out.println("Message is: " + e.getMessage());
  System.out.println("Description link is: " + e.getDescriptionLink());
} catch (Exception e) {

}
```

Example Response
```java
{
  "id": "cd003123-7ec9-4f3a-9d7c-f2de93e83e49",
}
```
