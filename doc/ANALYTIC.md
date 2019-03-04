## Total Line
**Get data grouped by hour (data refresh every 5 minutes).**
Track video playback on any [metric](https://docs.uiza.io/#analytic-metrics) performance, so you can know exactly what’s happening on every user’s device and debug more effectively.
See details [here](https://docs.uiza.io/#total-line).

```java
import io.uiza.model.Analytic;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

Map<String, Object> params = new HashMap<>();
params.put("start_date", "2019-01-01 07:00");
params.put("end_date", "2019-03-01 07:00");
params.put("metric", Metric.REBUFFER_PERCENTAGE.toString());

try {
  JsonArray analytics = Analytic.getTotalLine(params);
  JsonObject analytic = analytics.get(0).getAsJsonObject();
  System.out.println(analytic);
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
    "date_time": 1542978000000,
    "rebuffer_count": 1.6666666666666667
  },
  {
    "date_time": 1543204800000,
    "rebuffer_count": 0.5
  },
  {
    "date_time": 1543215600000,
    "rebuffer_count": 5
  }
]
```

## Type
Get data base on 5 type of filter: **country, device, title, player, os**.
See details [here](https://docs.uiza.io/#type).

```java
import io.uiza.model.Analytic;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

Map<String, Object> params = new HashMap<>();
params.put("start_date", "2019-01-01");
params.put("end_date", "2019-03-01");
params.put("type_filter", TypeFilter.COUNTRY.toString());

try {
  JsonArray analytics = Analytic.getType(params);
  JsonObject analytic = analytics.get(0).getAsJsonObject();
  System.out.println(analytic);
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
    "name": "Vietnam",
    "total_view": 15,
    "percentage_of_view": 0.625
  },
  {
    "name": "Other",
    "total_view": 9,
    "percentage_of_view": 0.375
  }
]
```

## Line
**Get data grouped by hour.**
Get total view in time range.
This help you to draw a line chart to visualize data.
See details [here](https://docs.uiza.io/#line).

```java
import io.uiza.model.Analytic;

Uiza.apiDomain = "<YOUR_WORKSPACE_API_DOMAIN>";
Uiza.apiKey = "<YOUR_API_KEY>";

Map<String, Object> params = new HashMap<>();
params.put("start_date", "2019-01-01");
params.put("end_date", "2019-03-01");
params.put("type", Metric.REBUFFER_COUNT.toString());

try {
  JsonArray analytics = Analytic.getLine(params);
  JsonObject analytic = analytics.get(0).getAsJsonObject();
  System.out.println(analytic);
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
    "day": 1541548800000,
    "total_view": 4
  },
  {
    "day": 1541635200000,
    "total_view": 5
  },
  {
    "day": 1541721600000,
    "total_view": 5
  },
  {
    "day": 1541980800000,
    "total_view": 1
  },
  {
    "day": 1542240000000,
    "total_view": 1
  },
  {
    "day": 1542499200000,
    "total_view": 1
  },
  {
    "day": 1542585600000,
    "total_view": 1
  }
]
```
