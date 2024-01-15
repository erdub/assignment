# Home assignment

### Prerequisites

* Requires at least Java 14 to run
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
finally:
```
mvn spring-boot:run
```

when running, [check out Swagger](http://localhost:8080/swagger-ui/index.html)