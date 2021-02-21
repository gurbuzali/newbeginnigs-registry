# NewBeginnings Registry

NewBeginnings Registry is a participant registry microservice which can
be used to register new participants and manage existing participants.

## Prerequisite

The project uses `maven` to build and `Java 8` to run.

## Getting Started

The project can be built using the maven wrapper at the root of the
project directory.

```shell script
./mvnw package -DskipTests
```

This should create an executable JAR in `target` directory with the
name `registry-${version}.jar`. You can then start the service by
executing below command:

```shell script
java -jar target/registry-{version}.jar
```

The application should start with the default port `8080`.

## API

### ADD

#### Request

`POST /api/v1/add`

`curl -i -H 'Content-Type: application/json' -d '{"name":"the name", "dateOfBirth":"2011-11-11", "phoneNumber":"the phone number", "address":"the address"}' http://localhost:8080/api/v1/add`

#### Response

```
HTTP/1.1 200 
Content-Type: text/plain;charset=UTF-8
Content-Length: 36
Date: Sun, 21 Feb 2021 13:58:19 GMT

d6a5b388-98f0-458b-b726-5c4c8093d7bf
```

### GET

#### Request

`GET /api/v1/get/refNo`

`curl -i -H 'Content-Type: application/json' http://localhost:8080/api/v1/get/d6a5b388-98f0-458b-b726-5c4c8093d7bf`

#### Response

```
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 21 Feb 2021 14:12:16 GMT

{"referenceNumber":"d6a5b388-98f0-458b-b726-5c4c8093d7bf","name":"the name","dateOfBirth":"2011-11-11T00:00:00.000+00:00","phoneNumber":"the phone number","address":"the address"}
```

### UPDATE PHONE NUMBER

#### Request

`POST /api/v1/updatePhoneNumber/refNo`

`curl -i -H 'Content-Type: application/json' -d 'new phone number' http://localhost:8080/api/v1/updatePhoneNumber/d6a5b388-98f0-458b-b726-5c4c8093d7bf`

#### Response

```
HTTP/1.1 200 
Content-Type: text/plain;charset=UTF-8
Content-Length: 16
Date: Sun, 21 Feb 2021 14:13:10 GMT

the phone number
```

### UPDATE ADDRESS

#### Request

`POST /api/v1/updateAddress/refNo`

`curl -i -H 'Content-Type: application/json' -d 'new address' http://localhost:8080/api/v1/updateAddress/d6a5b388-98f0-458b-b726-5c4c8093d7bf`

#### Response

```
HTTP/1.1 200 
Content-Type: text/plain;charset=UTF-8
Content-Length: 11
Date: Sun, 21 Feb 2021 14:13:50 GMT

the address
```

### DELETE

#### Request

`POST /api/v1/delete/refNo`

`curl -i -H 'Content-Type: application/json' --request POST http://localhost:8080/api/v1/delete/d6a5b388-98f0-458b-b726-5c4c8093d7bf`

#### Response

```
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 21 Feb 2021 14:16:08 GMT

{"referenceNumber":"d6a5b388-98f0-458b-b726-5c4c8093d7bf","name":"the name","dateOfBirth":"2011-11-11T00:00:00.000+00:00","phoneNumber":"new phone number","address":"new address"}
```
