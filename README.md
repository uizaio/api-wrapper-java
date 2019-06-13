# Uiza

## Introduction

This is documents the public API for Uiza version 4.0.
See documents for public API for Uiza version 3.0 [here](https://github.com/uizaio/api-wrapper-java/tree/master_v3). Please use maven dependency version 1.1.1 to use API v3

The Uiza API is organized around RESTful standard.
Our API has predictable, resource-oriented URLs, and uses HTTP response codes to indicate API errors.
JSON is returned by all API responses, including errors, although our API libraries convert responses to appropriate language-specific objects.

All API requests must be made over HTTPS. Calls made over plain HTTP will fail. API requests without authentication will also fail.

## Requirements

Java 8 or later.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>io.uiza</groupId>
  <artifactId>uiza-java</artifactId>
  <version>1.2.0</version>
</dependency>
```

## Documentation

Please see the [API docs](https://docs.uiza.io/v4) for the most up-to-date documentation.

## Usage

The library needs to be configured with your account's `authorization` and `appId`.
Set `Uiza.authorization` and `Uiza.appId` with your values:

```java
Uiza.authorization = "<your-authorization>";
Uiza.appId = "<your-app-id>";
```

## Entity

These below APIs used to take action with your media files (we called Entity).

You can see all available APIs for entity in [ENTITY.md](https://github.com/uizaio/api-wrapper-java/blob/master/doc/ENTITY.md).
See details [here](https://docs.uiza.io/v4/#video).

## Category

Category has been splits into 4 types: `folder`, `playlist`, `category` and `tag`. These will make the management of entity more easier.

You can see all available APIs for category in [CATEGORY.md](https://github.com/uizaio/api-wrapper-java/blob/master/doc/CATEGORY.md).
See details [here](https://docs.uiza.io/v4/#category).

## Storage

You can add your storage (`FTP`, `AWS S3`) with UIZA.
After synced, you can select your content easier from your storage to [create entity](https://docs.uiza.io/v4/#video).

You can see all available APIs for storage in [STORAGE.md](https://github.com/uizaio/api-wrapper-java/blob/master/doc/STORAGE.md).
See details [here](https://docs.uiza.io/v4/#storage).

## Live Streaming

These APIs used to create and manage live streaming event.

- When a Live is not start : it's named as `Event`.
- When have an `Event` , you can start it : it's named as `Feed`.

You can see all available APIs for live event in [LIVE.md](https://github.com/uizaio/api-wrapper-java/blob/master/doc/LIVE.md).
See details [here](https://docs.uiza.io/v4/#live-streaming).

## Callback

Callback used to retrieve an information for Uiza to your server, so you can have a trigger notice about an entity is upload completed and .

You can see all available APIs for callback in [CALLBACK.md](https://github.com/uizaio/api-wrapper-java/blob/master/doc/CALLBACK.md).
See details [here](https://docs.uiza.io/v4/#callback).

## User Management

You can manage user with APIs user. Uiza have 2 levels of user:

`Admin` - This account will have the highest priority, can have permission to create & manage users.

`User` - This account level is under Admin level. It only manages APIs that relates to this account.

You can see all available APIs for user management in [USER.md](https://github.com/uizaio/api-wrapper-java/blob/master/doc/USER.md).
See details [here](https://docs.uiza.io/v4/#user-management).

## Errors Code

Uiza uses conventional HTTP response codes to indicate the success or failure of an API request.
In general: Codes in the `2xx` range indicate success.
Codes in the `4xx` range indicate an error that failed given the information provided (e.g., a required parameter was omitted, a charge failed, etc.).
Codes in the `5xx` range indicate an error with Uiza's servers.

See details [here](https://github.com/uizaio/api-wrapper-ruby/blob/master/doc/ERRORS_CODE.md).

## Running SDK in local

You can follow steps below to create a UizaMan and run SDK in local
```
git clone https://github.com/uizaio/api-wrapper-java.git
cd api-wrapper-java
```

create new file src/main/java/uiza/UizaMain.java

```
package uiza;

import java.util.*;
import com.google.gson.*;

import io.uiza.Uiza;
import io.uiza.exception.*;
import io.uiza.model.*;
import io.uiza.model.Entity.*;

public class UizaMain {

  public static void main(String[] args) {
    Uiza.authorization = "your-authorization";

    try {
      JsonArray response = Entity.list();
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

Run file UizaMain.java in IDE

You can refer [PR#58](https://github.com/uizaio/api-wrapper-java/pull/58/files)

## Contributing

Bug reports and pull requests are welcome on GitHub at [Uiza-Java](https://github.com/uizaio/api-wrapper-java).

## License

Available as open source under the terms of the [MIT License](https://opensource.org/licenses/MIT).
