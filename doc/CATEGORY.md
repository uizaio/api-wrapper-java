# Category

## Create category

Create category for entity for easier management.
Category use to group all the same entities into a group (like folder/playlist/category or tag).
See details [here](https://docs.uiza.io/v4/#create-category).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Category;
import io.uiza.model.Category.*;

public class Main {

  public static void main(String[] args) {
    Uiza.appId = "d6342a7b4a6c40d2b851a54a4442ea83";
    Uiza.authorization = "uap-d6342a7b4a6c40d2b851a54a4442ea83-f3c977b7";

    Map<String, Object> params = new HashMap<>();
    params.put("name", "Folder sample");
    params.put("type", Type.FOLDER.toString());
    params.put("description", "Folder description");
    params.put("orderNumber", 1);

    try {
      JsonObject response = Category.create(params);
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
  "id": "f932aa79-852a-41f7-9adc-19935034f944",
  "name": "Playlist sample",
  "description": "Playlist description",
  "slug": "playlist-sample",
  "type": "playlist",
  "orderNumber": 3,
  "icon": "https:///example.com/image002.png",
  "status": 1,
  "createdAt": "2018-06-18T04:29:05.000Z",
  "updatedAt": "2018-06-18T04:29:05.000Z"
}
```

## Retrieve category

Get detail of category.
See details [here](https://docs.uiza.io/v4/#retrieve-category).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Category;
import io.uiza.model.Category.*;

public class Main {

  public static void main(String[] args) {
    Uiza.appId = "d6342a7b4a6c40d2b851a54a4442ea83";
    Uiza.authorization = "uap-d6342a7b4a6c40d2b851a54a4442ea83-f3c977b7";

    try {
      JsonObject response = Category.retrieve("e56934c6-979e-4d4e-8d9a-ec0b4248365c");
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
  "id": "f932aa79-852a-41f7-9adc-19935034f944",
  "name": "Playlist sample",
  "description": "Playlist description",
  "slug": "playlist-sample",
  "type": "playlist",
  "orderNumber": 3,
  "icon": "https:///example.com/image002.png",
  "status": 1,
  "createdAt": "2018-06-18T04:29:05.000Z",
  "updatedAt": "2018-06-18T04:29:05.000Z"
}
```

## Retrieve category list

Get all categories.
See details [here](https://docs.uiza.io/v4/#retrieve-category-list).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Category;
import io.uiza.model.Category.*;

public class Main {

  public static void main(String[] args) {
    Uiza.appId = "d6342a7b4a6c40d2b851a54a4442ea83";
    Uiza.authorization = "uap-d6342a7b4a6c40d2b851a54a4442ea83-f3c977b7";

    try {
      JsonArray response = Category.list();
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
    "id": "f932aa79-852a-41f7-9adc-19935034f944",
    "name": "Playlist sample",
    "description": "Playlist desciption",
    "slug": "playlist-sample",
    "type": "playlist",
    "orderNumber": 3,
    "icon": "/example.com/image002.png",
    "status": 1,
    "createdAt": "2018-06-18T04:29:05.000Z",
    "updatedAt": "2018-06-18T04:29:05.000Z"
  },
  {
    "id": "ab54db88-0c8c-4928-b1be-1e7120ad2c39",
    "name": "Folder sample",
    "description": "Folder's description",
    "slug": "folder-sample",
    "type": "folder",
    "orderNumber": 1,
    "icon": "/example.com/icon.png",
    "status": 1,
    "createdAt": "2018-06-18T03:17:07.000Z",
    "updatedAt": "2018-06-18T03:17:07.000Z"
  }
]
```

## Update category

Update information of category.
See details [here](https://docs.uiza.io/v4/#update-category).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Category;
import io.uiza.model.Category.*;

public class Main {

  public static void main(String[] args) {
    Uiza.appId = "d6342a7b4a6c40d2b851a54a4442ea83";
    Uiza.authorization = "uap-d6342a7b4a6c40d2b851a54a4442ea83-f3c977b7";

    Map<String, Object> params = new HashMap<>();
    params.put("name", "Folder edited");
    params.put("type", Type.FOLDER.toString());
    params.put("description", "Folder description new");
    params.put("orderNumber", 1);

    try {
      JsonObject response = Category.update("e56934c6-979e-4d4e-8d9a-ec0b4248365c", params);
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
  "id": "f932aa79-852a-41f7-9adc-19935034f944",
  "name": "Playlist sample",
  "description": "Playlist description",
  "slug": "playlist-sample",
  "type": "playlist",
  "orderNumber": 3,
  "icon": "https:///example.com/image002.png",
  "status": 1,
  "createdAt": "2018-06-18T04:29:05.000Z",
  "updatedAt": "2018-06-18T04:29:05.000Z"
}
```

## Delete category

Delete category.
See details [here](https://docs.uiza.io/v4/#delete-category).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Category;
import io.uiza.model.Category.*;

public class Main {

  public static void main(String[] args) {
    Uiza.appId = "d6342a7b4a6c40d2b851a54a4442ea83";
    Uiza.authorization = "uap-d6342a7b4a6c40d2b851a54a4442ea83-f3c977b7";

    try {
      JsonObject response = Category.delete("a257d0b7-4a58-44ac-b2ff-0cb26006daa4");
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
  "id": "095778fa-7e42-45cc-8a0e-6118e540b61d"
}
```

## Create category relation

Add relation for entity and category.
See details [here](https://docs.uiza.io/v4/#create-category-relation).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Category;
import io.uiza.model.Category.*;

public class Main {

  public static void main(String[] args) {
    Uiza.appId = "d6342a7b4a6c40d2b851a54a4442ea83";
    Uiza.authorization = "uap-d6342a7b4a6c40d2b851a54a4442ea83-f3c977b7";

    Map<String, Object> params = new HashMap<>();
    params.put("entityId", "680bef00-bb0c-4bed-9624-19109504fcfe");
    params.put("metadataIds", new String[] {"35a3e064-8aea-4a0a-a03c-d6927ad98ae1", "9b0c0c57-f85c-43d1-880b-23359c8374f4"});

    try {
      JsonArray response = Category.createRelation(params);
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
    "id": "5620ed3c-b725-4a9a-8ec1-ecc9df3e5aa6",
    "entityId": "16ab25d3-fd0f-4568-8aa0-0339bbfd674f",
    "metadataId": "095778fa-7e42-45cc-8a0e-6118e540b61d"
  },
  {
    "id": "47209e60-a99f-4c96-99fb-be4f858481b4",
    "entityId": "16ab25d3-fd0f-4568-8aa0-0339bbfd674f",
    "metadataId": "e00586b9-032a-46a3-af71-d275f01b03cf"
  }
]
```

## Delete category relation

Delete relation for entity and category.
See details [here](https://docs.uiza.io/v4/#delete-category-relation).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Category;
import io.uiza.model.Category.*;

public class Main {

  public static void main(String[] args) {
    Uiza.appId = "d6342a7b4a6c40d2b851a54a4442ea83";
    Uiza.authorization = "uap-d6342a7b4a6c40d2b851a54a4442ea83-f3c977b7";

    Map<String, Object> params = new HashMap<>();
    params.put("entityId", "680bef00-bb0c-4bed-9624-19109504fcfe");
    params.put("metadataIds", new String[] {"35a3e064-8aea-4a0a-a03c-d6927ad98ae1", "9b0c0c57-f85c-43d1-880b-23359c8374f4"});

    try {
      JsonArray response = Category.deleteRelation(params);
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
    "id": "5620ed3c-b725-4a9a-8ec1-ecc9df3e5aa6",
    "entityId": "16ab25d3-fd0f-4568-8aa0-0339bbfd674f",
    "metadataId": "095778fa-7e42-45cc-8a0e-6118e540b61d"
  },
  {
    "id": "47209e60-a99f-4c96-99fb-be4f858481b4",
    "entityId": "16ab25d3-fd0f-4568-8aa0-0339bbfd674f",
    "metadataId": "e00586b9-032a-46a3-af71-d275f01b03cf"
  }
]
```
