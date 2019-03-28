# Storage

## Add a storage

You can add your storage (`FTP`, `AWS S3`) with UIZA.
After synced, you can select your content easier from your storage to [create entity](https://docs.uiza.io/v4/#Media-create_entity).
See details [here](https://docs.uiza.io/v4/#add-a-storage).

### Add a FTP Storage

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Storage;
import io.uiza.model.Storage.*;

public class Main {

  public static void main(String[] args) {
    Uiza.appId = "d6342a7b4a6c40d2b851a54a4442ea83";
    Uiza.authorization = "uap-d6342a7b4a6c40d2b851a54a4442ea83-f3c977b7";

    Map<String, Object> params = new HashMap<>();
    params.put("name", "FTP Uiza");
    params.put("description", "FTP of Uiza, use for transcode");
    params.put("storageType", StorageType.FTP.toString());
    params.put("host", "ftp-example.uiza.io");
    params.put("username", "uiza");
    params.put("password", "=59x@LPsd+w7qW");
    params.put("port", 21);

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

### Add a S3 Storage

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Storage;
import io.uiza.model.Storage.*;

public class Main {

  public static void main(String[] args) {
    Uiza.appId = "d6342a7b4a6c40d2b851a54a4442ea83";
    Uiza.authorization = "uap-d6342a7b4a6c40d2b851a54a4442ea83-f3c977b7";

    Map<String, Object> params = new HashMap<>();
    params.put("name", "S3 Uiza");
    params.put("description", "S3 of Uiza, use for transcode");
    params.put("storageType", StorageType.S3.toString());
    params.put("host", "s3-example.uiza.io");
    params.put("username", "uiza");
    params.put("password", "=59x@LPsd+w7qW");
    params.put("port", 21);

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
See details [here](https://docs.uiza.io/v4/#retrieve-a-storage).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Storage;
import io.uiza.model.Storage.*;

public class Main {

  public static void main(String[] args) {
    Uiza.appId = "d6342a7b4a6c40d2b851a54a4442ea83";
    Uiza.authorization = "uap-d6342a7b4a6c40d2b851a54a4442ea83-f3c977b7";

    try {
      JsonObject response = Storage.retrieve("ca028a29-612c-40a8-add2-6dc811a2320f");
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
See details [here](https://docs.uiza.io/v4/#update-storage).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Storage;
import io.uiza.model.Storage.*;

public class Main {

  public static void main(String[] args) {
    Uiza.appId = "d6342a7b4a6c40d2b851a54a4442ea83";
    Uiza.authorization = "uap-d6342a7b4a6c40d2b851a54a4442ea83-f3c977b7";

    Map<String, Object> params = new HashMap<>();
    params.put("name", "FTP Uiza update");
    params.put("description", "FTP of Uiza, use for transcode update");
    params.put("storageType", StorageType.FTP.toString());

    try {
      JsonObject response = Storage.update("ca028a29-612c-40a8-add2-6dc811a2320f", params);
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
See details [here](https://docs.uiza.io/v4/#remove-storage).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Storage;
import io.uiza.model.Storage.*;

public class Main {

  public static void main(String[] args) {
    Uiza.appId = "d6342a7b4a6c40d2b851a54a4442ea83";
    Uiza.authorization = "uap-d6342a7b4a6c40d2b851a54a4442ea83-f3c977b7";

    try {
      JsonObject response = Storage.remove("444f0f71-e56b-449e-a9ec-435d3c974f16");
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
