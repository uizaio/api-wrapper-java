# Uiza

## Introduction

This is documents the public API for Uiza version 3.0.

The Uiza API is organized around RESTful standard.
Our API has predictable, resource-oriented URLs, and uses HTTP response codes to indicate API errors.
JSON is returned by all API responses, including errors, although our API libraries convert responses to appropriate language-specific objects.

All API requests must be made over HTTPS. Calls made over plain HTTP will fail. API requests without authentication will also fail.

## Authentication

In order, to use the Uiza, you should follow these steps:

* **Step 1:** Having an active Uiza account. (If you don't have, please get [here](https://id.uiza.io/)).
* **Step 2:** Once you have an Uiza account, you can get a Token to call the APIs.

This Token will have right & permission related with your account.

## Requirements

Java 8 or later.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>io.uiza</groupId>
  <artifactId>uiza-java</artifactId>
  <version>1.1.1</version>
</dependency>
```

## Documentation

Please see the [Java API docs](https://docs.uiza.io/) for the most up-to-date documentation.

## Usage

The library needs to be configured with your account's `workspaceApiDomain` and `authorization`.
See details [here](https://docs.uiza.io/#authentication).
Set `Uiza.workspaceApiDomain` and `Uiza.authorization` with your values:

```java
Uiza.workspaceApiDomain = "<your-workspace-api-domain.uiza.co>";
Uiza.authorization = "<your-authorization>";
```

## Entity

These below APIs used to take action with your media files (we called Entity).

You can see all available APIs for entity in [ENTITY.md](https://github.com/uizaio/api-wrapper-java/blob/master_v3/doc/ENTITY.md).
See details [here](https://docs.uiza.io/#video).

## Category

Category has been splits into 3 types: `folder`, `playlist` and `tag`. These will make the management of entity more easier.

You can see all available APIs for category in [CATEGORY.md](https://github.com/uizaio/api-wrapper-java/blob/master_v3/doc/CATEGORY.md).
See details [here](https://docs.uiza.io/#category).

## Storage

You can add your storage (`FTP`, `AWS S3`) with UIZA.
After synced, you can select your content easier from your storage to [create entity](https://docs.uiza.io/#create-entity).

You can see all available APIs for storage in [STORAGE.md](https://github.com/uizaio/api-wrapper-java/blob/master_v3/doc/STORAGE.md).
See details [here](https://docs.uiza.io/#storage).

## Live Streaming

These APIs used to create and manage live streaming event.

* When a Live is not start : it's named as `Event`.
* When have an `Event` , you can start it : it's named as `Feed`.

You can see all available APIs for live event in [LIVE.md](https://github.com/uizaio/api-wrapper-java/blob/master_v3/doc/LIVE.md).
See details [here](https://docs.uiza.io/#live-streaming).

## Callback

Callback used to retrieve an information for Uiza to your server, so you can have a trigger notice about an entity is upload completed and .

You can see all available APIs for callback in [CALLBACK.md](https://github.com/uizaio/api-wrapper-java/blob/master_v3/doc/CALLBACK.md).
See details [here](https://docs.uiza.io/#callback).

## Analytic

Monitor the four key dimensions of video QoS: playback failures, startup time, rebuffering, and video quality.
These 15 metrics help you track playback performance, so your team can know exactly what’s going on.

You can see all available APIs for analytic in [ANALYTIC.md](https://github.com/uizaio/api-wrapper-java/blob/master_v3/doc/ANALYTIC.md).
See details [here](https://docs.uiza.io/#analytic).

## User Management

You can manage user with APIs user. Uiza have 2 levels of user:

`Admin` - This account will have the highest priority, can have permission to create & manage users.

`User` - This account level is under Admin level. It only manages APIs that relates to this account.

You can see all available APIs for user management in [USER.md](https://github.com/uizaio/api-wrapper-java/blob/master_v3/doc/USER.md).
See details [here](https://docs.uiza.io/#user-management).

## Embed Metadata

Embed metadata is information that can be embed into video/audio file. You can embed into file by adding a json compose these tag.

See details [here](https://docs.uiza.io/#embed-metadata).

## Errors Code

Uiza uses conventional HTTP response codes to indicate the success or failure of an API request.
In general: Codes in the `2xx` range indicate success.
Codes in the `4xx` range indicate an error that failed given the information provided (e.g., a required parameter was omitted, a charge failed, etc.).
Codes in the `5xx` range indicate an error with Uiza's servers.

See details [here](https://github.com/uizaio/api-wrapper-ruby/blob/master_v3/doc/ERRORS_CODE.md).

## Contributing

Bug reports and pull requests are welcome on GitHub at [Uiza-Java](https://github.com/uizaio/api-wrapper-java).

## License

Available as open source under the terms of the [MIT License](https://opensource.org/licenses/MIT).
