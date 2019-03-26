# Storage

## Add a storage

You can add your storage (`FTP`, `AWS S3`) with UIZA.
After synced, you can select your content easier from your storage to [create entity](http://dev-ap-southeast-1-api.uizadev.io/docs/#api-Media-create_entity).
See details [here](http://dev-ap-southeast-1-api.uizadev.io/docs/#api-Media_Storage-create_storage).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Storage;
import io.uiza.model.Storage.*;

public class Main {

  public static void main(String[] args) {
    Uiza.authorization = "your-authorization";
    Uiza.appId = "your-app-id";

    Map<String, Object> params = new HashMap<>();
    params.put("name", "FTP Uiza");
    params.put("host", "ftp-example.uiza.io");
    params.put("port", 21);
    params.put("storageType", StorageType.FTP.toString());

    try {
      JsonObject response = Storage.add(params);
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
See details [here](http://dev-ap-southeast-1-api.uizadev.io/docs/#api-Media_Storage-list_storage).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Storage;
import io.uiza.model.Storage.*;

public class Main {

  public static void main(String[] args) {
    Uiza.authorization = "your-authorization";
    Uiza.appId = "your-app-id";

    try {
      JsonObject response = Storage.retrieve("<storage-id>");
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
See details [here](http://dev-ap-southeast-1-api.uizadev.io/docs/#api-Media_Storage-update_storage).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Storage;
import io.uiza.model.Storage.*;

public class Main {

  public static void main(String[] args) {
    Uiza.authorization = "your-authorization";
    Uiza.appId = "your-app-id";

    Map<String, Object> params = new HashMap<>();
    params.put("name", "FTP Uiza");
    params.put("host", "ftp-example.uiza.io");
    params.put("port", 21);
    params.put("storageType", StorageType.FTP.toString());

    try {
      JsonObject response = Storage.update("<storage-id>", params);
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
See details [here](http://dev-ap-southeast-1-api.uizadev.io/docs/#api-Media_Storage-delete_storage).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Storage;
import io.uiza.model.Storage.*;

public class Main {

  public static void main(String[] args) {
    Uiza.authorization = "your-authorization";
    Uiza.appId = "your-app-id";

    try {
      JsonObject response = Storage.remove("<storage-id>");
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
  "id": "cd003123-7ec9-4f3a-9d7c-f2de93e83e49",
}
```
