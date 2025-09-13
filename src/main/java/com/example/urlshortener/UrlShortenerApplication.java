package com.example.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Makes the application in my Canvas a runnable Spring Boot application
public class UrlShortenerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerApplication.class, args); // This tells Spring Boot to start up, and the first arg in run
                                                                    // tells Spring where the main application is located
    }

}
