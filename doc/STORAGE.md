# Storage

## Add a storage

You can add your storage (`FTP`, `AWS S3`) with UIZA.
After synced, you can select your content easier from your storage to [create entity](https://docs.uiza.io/#create-entity).
See details [here](https://docs.uiza.io/#add-a-storage).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Storage;
import io.uiza.model.Storage.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

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
See details [here](https://docs.uiza.io/#retrieve-a-storage).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Storage;
import io.uiza.model.Storage.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

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
See details [here](https://docs.uiza.io/#update-storage).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Storage;
import io.uiza.model.Storage.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

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
See details [here](https://docs.uiza.io/#remove-storage).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Storage;
import io.uiza.model.Storage.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

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
