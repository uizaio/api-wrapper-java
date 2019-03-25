# Category

## Create category

Create category for entity for easier management.
Category use to group all the same entities into a group (like Folder/ playlist/or tag).
See details [here](https://docs.uiza.io/#create-category).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Category;
import io.uiza.model.Category.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

    Map<String, Object> params = new HashMap<>();
    params.put("name", "Playlist Sample");
    params.put("type", Type.PLAYLIST.toString());

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
See details [here](https://docs.uiza.io/#retrieve-category).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Category;
import io.uiza.model.Category.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

    try {
      JsonObject response = Category.retrieve("<category-id>");
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
See details [here](https://docs.uiza.io/#retrieve-category-list).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Category;
import io.uiza.model.Category.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

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
See details [here](https://docs.uiza.io/#update-category).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Category;
import io.uiza.model.Category.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

    Map<String, Object> params = new HashMap<>();
    params.put("name", "Playlist Sample");
    params.put("type", Type.PLAYLIST.toString());

    try {
      JsonObject response = Category.update("<category-id>", params);
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
See details [here](https://docs.uiza.io/#delete-category).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Category;
import io.uiza.model.Category.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

    try {
      JsonObject response = Category.delete("<category-id>");
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
See details [here](https://docs.uiza.io/#create-category-relation).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Category;
import io.uiza.model.Category.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

    Map<String, Object> params = new HashMap<>();
    params.put("entityId", "<entity-id>");
    params.put("metadataIds", new String[] {"<category-id-1>", "<category-id-2>"});

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
See details [here](https://docs.uiza.io/#delete-category-relation).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Category;
import io.uiza.model.Category.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

    Map<String, Object> params = new HashMap<>();
    params.put("entityId", "<entity-id>");
    params.put("metadataIds", new String[] {"<category-id-1>", "<category-id-2>"});

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
