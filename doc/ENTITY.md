## Create entity
Create entity using full URL. Direct HTTP, FTP or AWS S3 link are acceptable.
See details [here](https://docs.uiza.io/#create-entity).

```java
import io.uiza.model.Entity;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

Map<String, Object> params = new HashMap<>();
params.put("name", "Sample Video");
params.put("url", "https://example.com/video.mp4");
params.put("inputType", InputType.HTTP.getInputType());
params.put("description", "Lorem Ipsum is simply dummy text of the printing and typesetting industry");
params.put("shortDescription", "Lorem Ipsum is simply dummy text.");
params.put("poster", "https://example.com/picture001.jpeg");
params.put("thumbnail", "https://example.com/picture002.jpeg");

try {
  JsonObject entity = Entity.create(params);
  System.out.println(entity.get("id"));
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
  "id": "16ab25d3-fd0f-4568-8aa0-0339bbfd674f",
  "name": "Sample Video",
  "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
  "shortDescription": "Lorem Ipsum is simply dummy text.",
  "view": 0,
  "poster": "https://example.com/picture001",
  "thumbnail": "https://example.com/picture002",
  "type": "vod",
  "status": 1,
  "duration": "237.865215",
  "publishToCdn":"success",
  "embedMetadata": {
      "artist": "John Doe",
      "album": "Album sample",
      "genre": "Pop"
  },
  "extendMetadata": {
      "movie_category":"action",
      "imdb_score":8.8,
      "published_year":"2018"
  },
  "createdAt": "2018-06-16T18:54:15.000Z",
  "updatedAt": "2018-06-16T18:54:29.000Z"
}
```

## Retrieve entity
Get detail of entity including all information of entity.
See details [here](https://docs.uiza.io/#retrieve-an-entity).

```java
import io.uiza.model.Entity;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

try {
  JsonObject entity = Entity.retrieve("<your-entity-id>");
  System.out.println(entity.get("name"));
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
  "id": "16ab25d3-fd0f-4568-8aa0-0339bbfd674f",
  "name": "Sample Video",
  "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
  "shortDescription": "Lorem Ipsum is simply dummy text.",
  "view": 0,
  "poster": "https://example.com/picture001",
  "thumbnail": "https://example.com/picture002",
  "type": "vod",
  "status": 1,
  "duration": "237.865215",
  "publishToCdn":"success",
  "embedMetadata": {
      "artist": "John Doe",
      "album": "Album sample",
      "genre": "Pop"
  },
  "extendMetadata": {
      "movie_category":"action",
      "imdb_score":8.8,
      "published_year":"2018"
  },
  "createdAt": "2018-06-16T18:54:15.000Z",
  "updatedAt": "2018-06-16T18:54:29.000Z"
}
```

## List all entities
Get list of entities including all detail.
See details [here](https://docs.uiza.io/#list-all-entities).

```java
import io.uiza.model.Entity;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

Map<String, Object> params = new HashMap<>();
params.put("publishToCdn", PublishStatus.NOT_READY.getStatus());
params.put("metadataId", "<your-folder/playlist-id>");

try {
  JsonArray entities = Entity.list(params);
  JsonObject firstEntity = entities.get(0).getAsJsonObject();
  System.out.println(firstEntity.get("id"));
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
    "id": "42ceb1ab-18ef-4f2e-b076-14299756d182",
    "name": "Sample Video 1",
    "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
    "shortDescription": "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
    "view": 0,
    "poster": "https://example.com/picture001",
    "thumbnail": "https://example.com/picture002",
    "type": "vod",
    "duration": "237.865215",
    "publishToCdn":"success",
    "embedMetadata": {
        "artist": "John Doe",
        "album": "Album sample",
        "genre": "Pop"
    },
    "extendMetadata": {
        "movie_category":"action",
        "imdb_score":8.8,
        "published_year":"2018"
    },
    "createdAt": "2018-06-22T19:20:17.000Z",
    "updatedAt": "2018-06-22T19:20:17.000Z"
  },
  {
    "id": "64b15996-2261-4f41-a3c4-72b652323f67",
    "name": "Sample Video 2",
    "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
    "shortDescription": "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
    "view": 0,
    "poster": "https://example.com/picture001",
    "thumbnail": "https://example.com/picture002",
    "type": "vod",
    "duration": "178.178105",
    "publishToCdn":"success",
    "embedMetadata": {
        "artist": "John Doe",
        "album": "Album sample",
        "genre": "Pop"
    },
    "extendMetadata": {
        "movie_category":"action",
        "imdb_score":8.8,
        "published_year":"2018"
    },
    "createdAt": "2018-06-22T19:16:22.000Z",
    "updatedAt": "2018-06-22T19:16:22.000Z"
  }
]
```

## Update entity
Update entity's information.
See details [here](https://docs.uiza.io/#update-an-entity).

```java
import io.uiza.model.Entity;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

Map<String, Object> params = new HashMap<>();
params.put("id", "<your-entity-id>");
params.put("name", "Name edited");
params.put("description", "Description edited");

try {
  JsonObject entity = Entity.update(params);
  System.out.println(entity.get("id"));
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
  "id": "16ab25d3-fd0f-4568-8aa0-0339bbfd674f",
  "name": "Name edited",
  "description": "Description edited",
  "shortDescription": "Lorem Ipsum is simply dummy text.",
  "view": 0,
  "poster": "https://example.com/picture001",
  "thumbnail": "https://example.com/picture002",
  "type": "vod",
  "status": 1,
  "duration": "237.865215",
  "publishToCdn":"success",
  "embedMetadata": {
      "artist": "John Doe",
      "album": "Album sample",
      "genre": "Pop"
  },
  "extendMetadata": {
      "movie_category":"action",
      "imdb_score":8.8,
      "published_year":"2018"
  },
  "createdAt": "2018-06-16T18:54:15.000Z",
  "updatedAt": "2018-06-16T18:54:29.000Z"
}
```

## Delete entity
Delete entity.
See details [here](https://docs.uiza.io/#delete-an-entity).

```java
import io.uiza.model.Entity;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

try {
  JsonObject entity = Entity.delete("<your-entity-id>");
  System.out.println(entity.get("id"));
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
  "id": "8b83886e-9cc3-4eab-9258-ebb16c0c73de"
}
```

## Search entity
Search entity base on keyword entered
See details [here](https://docs.uiza.io/#search-entity).

```java
import io.uiza.model.Entity;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

try {
  JsonArray entities = Entity.search(params);
  JsonObject firstEntity = entities.get(0).getAsJsonObject();
  System.out.println(firstEntity.get("id"));
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
    "id": "42ceb1ab-18ef-4f2e-b076-14299756d182",
    "name": "Sample Video 1",
    "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
    "shortDescription": "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
    "view": 0,
    "poster": "https://example.com/picture001",
    "thumbnail": "https://example.com/picture002",
    "type": "vod",
    "duration": "237.865215",
    "publishToCdn":"success",
    "embedMetadata": {
        "artist": "John Doe",
        "album": "Album sample",
        "genre": "Pop"
    },
    "extendMetadata": {
        "movie_category":"action",
        "imdb_score":8.8,
        "published_year":"2018"
    },
    "createdAt": "2018-06-22T19:20:17.000Z",
    "updatedAt": "2018-06-22T19:20:17.000Z"
  },
  {
    "id": "64b15996-2261-4f41-a3c4-72b652323f67",
    "name": "Sample Video 2",
    "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
    "shortDescription": "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
    "view": 0,
    "poster": "https://example.com/picture001",
    "thumbnail": "https://example.com/picture002",
    "type": "vod",
    "duration": "178.178105",
    "publishToCdn":"success",
    "embedMetadata": {
        "artist": "John Doe",
        "album": "Album sample",
        "genre": "Pop"
    },
    "extendMetadata": {
        "movie_category":"action",
        "imdb_score":8.8,
        "published_year":"2018"
    },
    "createdAt": "2018-06-22T19:16:22.000Z",
    "updatedAt": "2018-06-22T19:16:22.000Z"
  }
]
```

## Publish entity to CDN
Publish entity to CDN, use for streaming
See details [here](https://docs.uiza.io/#publish-entity-to-cdn).

```java
import io.uiza.model.Entity;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

try {
  JsonObject response = Entity.publish("<your-entity-id>");
  System.out.println(response.get("message"));
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
  "message": "Your entity started publish, check process status with this entity ID",
  "entityId": "42ceb1ab-18ef-4f2e-b076-14299756d182"
}
```

## Get status publish
Publish entity to CDN, use for streaming
See details [here](https://docs.uiza.io/#get-status-publish).

```java
import io.uiza.model.Entity;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

try {
  JsonObject response = Entity.getStatusPublish("<your-entity-id>");
  System.out.println(response.get("status"));
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
  "progress": 0,
  "status": "processing"
}
```

## Get AWS upload key
This API will be return the bucket temporary upload storage & key for upload, so that you can push your file to Uiza’s storage and get the link for URL upload & create entity
See details [here](https://docs.uiza.io/#get-aws-upload-key).

```java
import io.uiza.model.Entity;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

try {
  JsonObject response = Entity.getAwsUploadKey();
  System.out.println(response.get("region_name"));
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
  "temp_expire_at": 1533658598,
  "temp_access_id": "ASIAV*******GPHO2DTZ",
  "bucket_name": "uiza****-storage-ap-southeast-1-01/upload-temp/****ff4ad74a5195f4c/",
  "temp_session_token": "FQo///wEaDM3rrospITbBQ==",
  "region_name": "ap-southeast-1",
  "temp_access_secret": "dp****cx2mE2lZxsSq7kV++vWSL6RNatAhbqc"
}
```
