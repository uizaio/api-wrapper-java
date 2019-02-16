# Uiza

## Introduction
This is documents the public API for Uiza version 3.0.

The Uiza API is organized around RESTful standard.
Our API has predictable, resource-oriented URLs, and uses HTTP response codes to indicate API errors.
JSON is returned by all API responses, including errors, although our API libraries convert responses to appropriate language-specific objects.

All API requests must be made over HTTPS. Calls made over plain HTTP will fail. API requests without authentication will also fail.

## Authentication
In order, to use the Uiza, you should follow these steps:

* **Step 1:** Having an active Uiza account. (If you don't have, please get [here](https://id.uiza.io/))
* **Step 2:** Once you have an Uiza account, you can get a Token to call the APIs.

This Token will have right & permission related with your account.

## Requirements

Java 1.8 or later.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>co.uiza.apiwrapper</groupId>
  <artifactId>uiza</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Documentation

Please see the [Java API docs](https://docs.uiza.io/) for the most up-to-date documentation.

## Usage
The library needs to be configured with your account's `API_DOMAIN` and `API_KEY`.
See details [here](https://docs.uiza.io/#authentication).
Set `Uiza.apiDomain` and `Uiza.apiKey` with your values:

```java
Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";
```

## Entity
These below APIs used to take action with your media files (we called Entity).

You can see all available APIs for entity in [ENTITY.md](https://github.com/uizaio/api-wrapper-java/blob/develop/doc/ENTITY.md).
See details [here](https://docs.uiza.io/#video).

## Category
Category has been splits into 3 types: `folder`, `playlist` and `tag`. These will make the management of entity more easier.

See details [here](https://docs.uiza.io/#category).

## Storage
You can add your storage (`FTP`, `AWS S3`) with UIZA.
After synced, you can select your content easier from your storage to [create entity](https://docs.uiza.io/#create-entity).

See details [here](https://docs.uiza.io/#storage).

## Live Streaming
These APIs used to create and manage live streaming event.
* When a Live is not start : it's named as `Event`.
* When have an `Event` , you can start it : it's named as `Feed`.

See details [here](https://docs.uiza.io/#live-streaming).

## Callback
Callback used to retrieve an information for Uiza to your server, so you can have a trigger notice about an entity is upload completed and .

See details [here](https://docs.uiza.io/#callback).

## Analytic
Monitor the four key dimensions of video QoS: playback failures, startup time, rebuffering, and video quality.
These 15 metrics help you track playback performance, so your team can know exactly what’s going on.

See details [here](https://docs.uiza.io/#analytic).

## Embed Metadata
Embed metadata is information that can be embed into video/audio file. You can embed into file by adding a json compose these tag.

See details [here](https://docs.uiza.io/#embed-metadata).

## Errors Code
Uiza uses conventional HTTP response codes to indicate the success or failure of an API request.
In general: Codes in the `2xx` range indicate success.
Codes in the `4xx` range indicate an error that failed given the information provided (e.g., a required parameter was omitted, a charge failed, etc.).
Codes in the `5xx` range indicate an error with Uiza's servers.

See details [here](https://github.com/uizaio/api-wrapper-ruby/blob/develop/doc/ERRORS_CODE.md).

## Contributing

Bug reports and pull requests are welcome on GitHub at https://github.com/uizaio/api-wrapper-java.

## License

Available as open source under the terms of the [MIT License](https://opensource.org/licenses/MIT).
