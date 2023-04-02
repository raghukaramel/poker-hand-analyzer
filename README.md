# Poker Hand Analyzer Service
A SpringBoot based microservice to analyze poker hand.

### Tech Stack
* JDK 11 or higher
* SpringBoot 2.7.10
* Apache Maven 3.9.0

### Project Structure - Java Packages
* [com.quicken.annotation](./src/main/java/com/quicken/annotation) - Custom Annotations
* [com.quicken.config](./src/main/java/com/quicken/config) - Custom configurations
* [com.quicken.constant](./src/main/java/com/quicken/constant) - Constants used across application
* [com.quicken.controller](./src/main/java/com/quicken/controller) - REST Controllers
* [com.quicken.dto](./src/main/java/com/quicken/dto) - Data transfer Objects
* [com.quicken.handler](./src/main/java/com/quicken/handler) - Global Exception Handlers
* [com.quicken.service](./src/main/java/com/quicken/service) - Poker Hand Analyzer Business Services
* [com.quicken.util](./src/main/java/com/quicken/Util) - Utility Classes
* [com.quicken.validator](./src/main/java/com/quicken/validator) - Custom Validators

### How to build locally?
Please ensure that you have the software mentioned in the [Tech Stack](#tech-stack) section. 
Then run the following command from a terminal window `mvn clean package`

### How to run unit tests and get code coverage report?
Run command `mvn clean test` from command line. 

`mvn clean test` will also generate code coverage report using Jacoco and that report 
can be found **[here](./target/site/jacoco/index.html)**. You can open that in your favourite
browser and check the coverage report.

### How to run it locally?
Run command `mvn clean spring-boot:run` from command line. It will start the application locally
and listens on port 8080. You can use the **[Swagger UI](http://localhost:8080/swagger-ui/)** included 
in the application to test the APIs.

### Other ways to test it locally
You can any REST API Client to test. The popular REST API client are **Postman**, **curl** etc. 
You can find a **[Postman Collection here](./src/test/resources/poker-hand-analyzer-postman-collection.json)** 
that can be imported into your Postman and start testing. 

Also below are some curl examples you can use for testing.

### curl examples
#### Low end straight with A
```shell
curl --location 'http://localhost:8080/v1/pokerHands/isStraight' \
--header 'Content-Type: application/json' \
--data '{
    "cards": 
    [
        {"suit": "D", "value": "2"},
        {"suit": "H", "value": "3"},
        {"suit": "C", "value": "4"},
        {"suit": "S", "value": "5"},
        {"suit": "D", "value": "A"},
        {"suit": "H", "value": "Q"},
        {"suit": "H", "value": "K"}
    ]
}
'
```

#### High end straight with A
```shell
curl --location 'http://localhost:8080/v1/pokerHands/isStraight' \
--header 'Content-Type: application/json' \
--data '{
    "cards": 
    [
        {"suit": "D", "value": "2"},
        {"suit": "H", "value": "3"},
        {"suit": "C", "value": "10"},
        {"suit": "S", "value": "J"},
        {"suit": "D", "value": "A"},
        {"suit": "H", "value": "Q"},
        {"suit": "H", "value": "K"}
    ]
}
'
```

#### Straight
```shell
curl --location 'http://localhost:8080/v1/pokerHands/isStraight' \
--header 'Content-Type: application/json' \
--data '{
    "cards": 
    [
        {"suit": "D", "value": "2"},
        {"suit": "H", "value": "3"},
        {"suit": "C", "value": "10"},
        {"suit": "S", "value": "J"},
        {"suit": "D", "value": "7"},
        {"suit": "H", "value": "8"},
        {"suit": "H", "value": "9"}
    ]
}
'
```

#### Invalid input - Multiple input errors
```shell
curl --location 'http://localhost:8080/v1/pokerHands/isStraight' \
--header 'Content-Type: application/json' \
--data '{
    "cards": 
    [
        {"suit": "DZ", "value": "2"},
        {"suit": "C", "value": "10"},
        {"suit": "C", "value": "10"},
        {"suit": "S", "value": "JJ"},
        {"suit": "D", "value": "7"},
        {"suit": "H", "value": "8"}
    ]
}
'
```

#### Invalid input - Duplicate cards
```shell
curl --location 'http://localhost:8080/v1/pokerHands/isStraight' \
--header 'Content-Type: application/json' \
--data '{
    "cards": 
    [
        {"suit": "D", "value": "2"},
        {"suit": "C", "value": "10"},
        {"suit": "C", "value": "10"},
        {"suit": "S", "value": "J"},
        {"suit": "D", "value": "7"},
        {"suit": "H", "value": "8"},
        {"suit": "H", "value": "9"}
    ]
}
'
```

#### Invalid input - 8 cards
```shell
curl --location 'http://localhost:8080/v1/pokerHands/isStraight' \
--header 'Content-Type: application/json' \
--data '{
    "cards": 
    [
        {"suit": "D", "value": "2"},
        {"suit": "H", "value": "3"},
        {"suit": "C", "value": "10"},
        {"suit": "S", "value": "J"},
        {"suit": "D", "value": "7"},
        {"suit": "H", "value": "8"},
        {"suit": "H", "value": "9"},
        {"suit": "H", "value": "Q"}
    ]
}
'
```

#### Invalid input - 6 cards
```shell
curl --location 'http://localhost:8080/v1/pokerHands/isStraight' \
--header 'Content-Type: application/json' \
--data '{
    "cards": 
    [
        {"suit": "D", "value": "2"},
        {"suit": "H", "value": "3"},
        {"suit": "C", "value": "10"},
        {"suit": "S", "value": "J"},
        {"suit": "D", "value": "7"},
        {"suit": "H", "value": "8"}
    ]
}
'
```