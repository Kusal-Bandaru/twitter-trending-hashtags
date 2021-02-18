# twitter-trending-hashtags
[Spring Boot](http://projects.spring.io/spring-boot/) app to retrieve top trending hashtags from the input tweets

## Requirements

For building and running the application we need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on our local machine. One way is to execute the `main` method in the `com.twitter.trending.hashtags.TwitterTrendingHashtagsApplication` class from the IDE.

Alternatively we can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
## Database details

[H2 Database](https://www.h2database.com/html/main.html) is used which provides embedded DB. It is used to facilitate the testing purposes so that the over head about configuring the local database in different systems can be avoided. However, H2 comes with volatile memory which means that the data will be lost when we stop serving the application.

H2 provides a console with user interface which can be accessed through a browser from below URL.

```
http://localhost:8080/h2-console
```
Below is the JDBC URL to connect to the DB. We can connect to the DB with defauls credentials itself.
```
JDBC URL: jdbc:h2:mem:twitterdb
```
We can view the tables and records from with in the console itself by running the queries which makes the configuration to be done for testing the application almost none.

## Documentation details

[Swagger](https://swagger.io/) is used for the API documentation. It provides the details about the Rest APIs and endpoints along with the operations that can be performed from the respective APIs.

Swagger provides the documentation through it's own user interface which can be accessed from the below URL. Port in the below URL specifies the local port where the application is being served.

```
http://localhost:port/swagger-ui.html
```
We can also interact with the APIs by making rest calls from Swagger UI which makes it a great product. We can make rest API calls same as postman with request body and it provides us the response respectively.
