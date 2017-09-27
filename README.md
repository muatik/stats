# Stats-app

[![Build Status](https://travis-ci.org/muatik/stats.svg?branch=master)](https://travis-ci.org/muatik/stats)

This is a REST API application which exposes necessary functionality to receive statistics and serve summary information.

The main use case for this application is to calculate realtime statistic from the last seconds specified in the configuration file.
There are two APIs, one of them is called every time a transaction is made. It is also the sole input of this rest API.
The other one returns the statistic based of the transactions of the last 60 seconds.


## REST APIs

#### Send a new statistic
##### Request
```http
POST /transactions HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
	"amount": 110,
	"timestamp": 1505252993000
}
```

##### Response
201 - in case of success
204 - if transaction is older than 60 seconds

```http
HTTP/1.1 201 Created
Content-Type: application/json


```

#### Get stats list
Request
```http
GET /statistics HTTP/1.1
Host: localhost:8080


```

Response
```http
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8

{
  "count":2,
  "sum":230.0,
  "avg":115.0,
  "min":110.0,
  "max":110.0
}
```


## Testing

Travis.CI is used for continuous testing. [for builds](https://travis-ci.org/muatik/news-app)

To test:
```
mvn clean test
```

## Build

In order to build this application, run the following maven command.

```
mvn clean package
```


## License

MIT
