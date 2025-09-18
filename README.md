URL Shortener 

!!! Project Overview
    This project is a simple, functional URL shortener built as a Minimum Viable Product (MVP) using Spring Boot. It demonstrates a complete web application with a RESTful API and database integration, making it an excellent portfolio project for a job application or resume.
    Technology Stack

        - Language: Java 21
        - Framework: Spring Boot 3.2.5
        - Build Tool: Maven
        - Database: H2 (an in-memory database)
        - Testing: JUnit 5 and Mockito

!!! Prerequisites
    Before you begin, ensure you have the following installed on your system:

        - Java Development Kit (JDK) 21
        - A code editor like Visual Studio Code
        - Maven

!!! Getting Started
    1. Project Structure

    The project directory should have the following structure:

    .
    ├── pom.xml
    └── src
        ├── main
        │   ├── java
        │   │   └── com
        │   │       └── fullersource
        │   │           └── urlshortener
        │   │               ├── UrlShortenerApplication.java
        │   │               ├── controller
        │   │               │   └── UrlController.java
        │   │               ├── model
        │   │               │   └── UrlMapping.java
        │   │               ├── repository
        │   │               │   └── UrlRepository.java
        │   │               └── service
        │   │                   └── UrlService.java
        │   └── resources
        │       └── application.properties
        └── test
            └── java
                └── com
                    └── fullersource
                        └── urlshortener
                            └── UrlShortenerApplicationTests.java

    2. Build the project

        Use Maven to build the project. This will download all the necessary dependencies.

            mvn clean install

    3. Run the application

        You can run the application using the Spring Boot Maven plugin.

            mvn spring-boot:run

        The application will start on http://localhost:8080.

    4. Run the tests

        You can run the unit tests to verify the core logic.

            mvn test

!!! API Endpoints
    1. Shorten a URL

        - Endpoint: POST /shorten
        - Request Body: The long URL as a plain string (e.g., https://www.example.com/very/long/url).
        - Response: A 200 OK status with the generated short key as a plain string.
        - Hint: Make sure you are running these commands on a new terminal, while running the application.

        Example using curl:

            curl -X POST -H "Content-Type: text/plain" -d "https://www.google.com" http://localhost:8080/shorten

    2. Redirect from a Short Key

        - Endpoint: GET /{shortKey}
        - Request: A GET request with the short key in the path.
        - Response: A 302 Found status, which will automatically redirect the browser or client to the original long URL. If the short key is not found, it returns a 404 Not Found.

        Example using curl:

            curl -i http://localhost:8080/4fJ5kL

        (Note: The short key 4fJ5kL is an example; yours will be an 8-character key.)