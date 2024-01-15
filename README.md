# Home assignment

### Prerequisites

* Requires at least Java 14 to run (developed using Java 17)
* Requires external datasource

### How to build

```
mvn clean install
```

### How to test

```
mvn test
```

### How to run

First, you need to setup external storage.
To do so:
```
cd env/local/
```
and run docker-compose script by typing:
```
docker-compose up
```
finally, go back to root directory and type:
```
mvn spring-boot:run
```

when running, [check out Swagger](http://localhost:8080/swagger-ui/index.html)