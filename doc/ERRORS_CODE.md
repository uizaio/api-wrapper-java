# Errors Code

Uiza uses conventional HTTP response codes to indicate the success or failure of an API request.
In general: Codes in the `2xx` range indicate success.
Codes in the `4xx` range indicate an error that failed given the information provided (e.g., a required parameter was omitted, a charge failed, etc.).
Codes in the `5xx` range indicate an error with Uiza's servers.

See details [here](https://docs.uiza.io/#errors-code).

## HTTP status code summary

| Error Code                  | Detail                                                                    |
| ---------------------------:|:--------------------------------------------------------------------------|
| 200 - OK                    | Everything worked as expected.                                            |
| 400 - Bad Request           | The request was unacceptable, often due to missing a required parameter.  |
| 401 - Unauthorized          | No valid API key provided.                                                |
| 404 - Not Found             | The requested resource doesn't exist.                                     |
| 422 - Unprocessable         | The syntax of the request is correct (often cause of wrong parameter).    |
| 500 - Internal Server Error | We had a problem with our server. Try again later.                        |
| 503 - Service Unavailable   | The server is overloaded or down for maintenance.                         |

## Exception types

| Exception Type                      | Detail                                                                    |
| -----------------------------------:|:--------------------------------------------------------------------------|
| 400 - BadRequestException           | The request was unacceptable, often due to missing a required parameter.  |
| 401 - UnauthorizedException         | No valid API key provided.                                                |
| 404 - NotFoundException             | The requested resource doesn't exist.                                     |
| 422 - UnprocessableException        | The syntax of the request is correct (often cause of wrong parameter).    |
| 500 - InternalServerException       | We had a problem with our server. Try again later.                        |
| 503 - ServiceUnavailableException   | The server is overloaded or down for maintenance.                         |
| 4xx - ClientException               | The error seems to have been caused by the client.                        |
| 5xx - ServerException               | The server is aware that it has encountered an error.                     |

## Handling exceptions

Our API libraries raise exceptions for many reasons, such as a invalid parameters, authentication errors, and network unavailability. We recommend writing code that gracefully handles all possible API exceptions.

### Example Request

```java
  try {
    // Use Uiza's library to make requests
  } catch (BadRequestException e) {
    System.out.println("Status is: " + e.getStatusCode());
    System.out.println("Message is: " + e.getMessage());
    System.out.println("Description link is: " + e.getDescriptionLink());
  } catch (UnauthorizedException e) {
    // The request was unacceptable, often due to missing a required parameter
  } catch (NotFoundException e) {
    // The requested resource doesn't exist
  } catch (UnprocessableException e) {
    // The syntax of the request is correct (often cause of wrong parameter)
  } catch (InternalServerException e) {
    // Problem with our server. Try again later
  } catch (ServiceUnavailableException e) {
    // The server is overloaded or down for maintenance
  } catch (ClientException e) {
    // The error seems to have been caused by the client
  } catch (ServerException e) {
    // The server is aware that it has encountered an error
  } catch (UizaException e) {
    // Display a very generic error to the user
  } catch (Exception e) {
    // Something else happened, completely unrelated to Uiza
  }
```
