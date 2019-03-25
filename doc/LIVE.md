# Live Event

## Create a live event

Create a live streaming and manage the live streaming input (output).
A live stream can be set up and start later or start right after set up.
Live Channel Minutes counts when the event starts.
See details [here](https://docs.uiza.io/#create-a-live-event).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Live;
import io.uiza.model.Live.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

    Map<String, Object> params = new HashMap<>();
    params.put("name", "<your-live-event-name>");
    params.put("mode", Mode.PULL.toString());
    params.put("encode", Encode.ENCODE.getVal());
    params.put("dvr", Dvr.ACTIVE_RECORD.getVal());
    params.put("linkStream", new String[] {"stream-url1.com", "stream-url2.com"});
    params.put("resourceMode", ResourceMode.SINGLE.toString());

    try {
      JsonObject response = Live.create(params);
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
  "id": "8b83886e-9cc3-4eab-9258-ebb16c0c73de",
  "name": "checking 01",
  "description": "checking",
  "mode": "pull",
  "resourceMode": "single",
  "encode": 0,
  "channelName": "checking-01",
  "lastPresetId": null,
  "lastFeedId": null,
  "poster": "https://example.com/poster.jpeg",
  "thumbnail": "https://example.com/thumbnail.jpeg",
  "linkPublishSocial": null,
  "linkStream": "[\"https://www.youtube.com/watch?v=pQzaHPoNX1I\"]",
  "lastPullInfo": null,
  "lastPushInfo": null,
  "lastProcess": null,
  "eventType": null,
  "createdAt": "2018-06-21T14:33:36.000Z",
  "updatedAt": "2018-06-21T14:33:36.000Z"
}
```

## Retrieve a live event

Retrieves the details of an existing event.
You need only provide the unique identifier of event that was returned upon Live event creation.
See details [here](https://docs.uiza.io/#retrieve-a-live-event).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Live;
import io.uiza.model.Live.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

    try {
      JsonObject response = Live.retrieve("<live-event-id>");
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
  "id": "8b83886e-9cc3-4eab-9258-ebb16c0c73de",
  "name": "checking 01",
  "description": "checking",
  "mode": "pull",
  "resourceMode": "single",
  "encode": 0,
  "channelName": "checking-01",
  "lastPresetId": null,
  "lastFeedId": null,
  "poster": "https://example.com/poster.jpeg",
  "thumbnail": "https://example.com/thumbnail.jpeg",
  "linkPublishSocial": null,
  "linkStream": "[\"https://www.youtube.com/watch?v=pQzaHPoNX1I\"]",
  "lastPullInfo": null,
  "lastPushInfo": null,
  "lastProcess": null,
  "eventType": null,
  "createdAt": "2018-06-21T14:33:36.000Z",
  "updatedAt": "2018-06-21T14:33:36.000Z"
}
```

## Update a live event

Update the specific Live event by edit values of parameters.
See details [here](https://docs.uiza.io/#update-a-live-event).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Live;
import io.uiza.model.Live.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

    Map<String, Object> params = new HashMap<>();
    params.put("name", "<your-live-event-name>");
    params.put("mode", Mode.PULL.toString());
    params.put("encode", Encode.ENCODE.getVal());
    params.put("dvr", Dvr.ACTIVE_RECORD.getVal());
    params.put("linkStream", new String[] {"stream-url1.com", "stream-url2.com"});
    params.put("resourceMode", ResourceMode.SINGLE.toString());

    try {
      JsonObject response = Live.update("<live-event-id>", params);
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
  "id": "8b83886e-9cc3-4eab-9258-ebb16c0c73de",
  "name": "checking 01",
  "description": "checking",
  "mode": "pull",
  "resourceMode": "single",
  "encode": 0,
  "channelName": "checking-01",
  "lastPresetId": null,
  "lastFeedId": null,
  "poster": "https://example.com/poster.jpeg",
  "thumbnail": "https://example.com/thumbnail.jpeg",
  "linkPublishSocial": null,
  "linkStream": "[\"https://www.youtube.com/watch?v=pQzaHPoNX1I\"]",
  "lastPullInfo": null,
  "lastPushInfo": null,
  "lastProcess": null,
  "eventType": null,
  "createdAt": "2018-06-21T14:33:36.000Z",
  "updatedAt": "2018-06-21T14:33:36.000Z"
}
```

## Start a live feed

Start a live event that has been create success. The Live channel minute start count whenever the event start success.
See details [here](https://docs.uiza.io/#start-a-live-feed).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Live;
import io.uiza.model.Live.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

    try {
      JsonObject response = Live.startFeed("<live-event-id>");
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
  "id": "8b83886e-9cc3-4eab-9258-ebb16c0c73de",
}
```

## Get views of live feed

Get a live view status . This view only show when event has been started and being processing.
See details [here](https://docs.uiza.io/#get-view-of-live-feed).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Live;
import io.uiza.model.Live.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

    try {
      JsonObject response = Live.getView("<live-event-id>");
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
  "stream_name": "peppa-pig-english-episodes",
  "watchnow": 1,
  "day": 1533271205999
}
```

## Stop a live feed

Stop live event.
See details [here](https://docs.uiza.io/#stop-a-live-feed).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Live;
import io.uiza.model.Live.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

    try {
      JsonObject response = Live.stopFeed("<live-event-id>");
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
  "id": "8b83886e-9cc3-4eab-9258-ebb16c0c73de",
}
```

## List all recorded files

Retrieves list of recorded file after streamed (only available when your live event has turned on Record feature).
See details [here](https://docs.uiza.io/#list-all-recorded-files).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Live;
import io.uiza.model.Live.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

    try {
      JsonArray response = Live.listRecorded();
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
    "id": "040df935-61c4-46f7-a41f-0a899ebaa2cc",
    "entityId": "ee122e85-553f-4621-bc77-1396191d5846",
    "channelName": "dcb8686f-d0f8-4a0f-8b92-22db339eb315",
    "feedId": "3e3b75df-e6fa-471c-b386-8f44b8a34b6c",
    "eventType": "pull",
    "startTime": "2018-12-13T16:28:29.000Z",
    "endTime": "2018-12-13T18:28:29.000Z",
    "length": "7200",
    "fileSize": "9276182",
    "extraInfo": null,
    "endpointConfig": "s3-uiza-dvr",
    "createdAt": "2018-12-13T19:28:43.000Z",
    "updatedAt": "2018-12-13T19:28:43.000Z",
    "entityName": "Christmas 2018 Holidays Special | Best Christmas Songs & Cartoons for Kids & Babies on Baby First TV"
  },
  {
    "id": "3fec45e9-932b-4efe-b97f-dc3053acaa05",
    "entityId": "47e804bc-d4e5-4442-8f1f-20341a156a70",
    "channelName": "e9034eac-4905-4f9a-8e79-c0bd67e49dd5",
    "feedId": "12830696-87e3-4209-a877-954f8f008964",
    "eventType": "pull",
    "startTime": "2018-12-13T14:14:14.000Z",
    "endTime": "2018-12-13T16:14:14.000Z",
    "length": "7200",
    "fileSize": "439858038",
    "extraInfo": null,
    "endpointConfig": "s3-uiza-dvr",
    "createdAt": "2018-12-13T17:30:42.000Z",
    "updatedAt": "2018-12-13T17:30:42.000Z",
    "entityName": "WATCH: SpaceX to Launch Falcon 9 Rocket #Spaceflight CRS16 @1:16pm EST"
  }
]
```

## Delete a record file

Delete a recorded file.
See details [here](https://docs.uiza.io/#delete-a-record-file).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Live;
import io.uiza.model.Live.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

    try {
      JsonObject response = Live.delete("<record-id>"); // Identifier of record (get from list record)
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
  "id": "8b83886e-9cc3-4eab-9258-ebb16c0c73de",
}
```

## Convert into VOD

Convert recorded file into VOD entity.
After converted, your file can be stream via Uiza's CDN.
See details [here](https://docs.uiza.io/#convert-into-vod).

```java
import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.Live;
import io.uiza.model.Live.*;

public class Main {

  public static void main(String[] args) {
    Uiza.workspaceApiDomain = "your-workspace-api-domain.uiza.co";
    Uiza.authorization = "your-authorization";

    try {
      JsonObject response = Live.convertToVod("<record-id>");
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
  "id": "8b83886e-9cc3-4eab-9258-ebb16c0c73de",
}
```
