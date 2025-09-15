package com.fullersource.urlshortener.controller;

import org.springframework.web.bind.annotation.RestController;
import com.fullersource.urlshortener.service.UrlService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import java.net.URISyntaxException;
import java.net.URI;

@RestController
public class UrlController {
    
    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public String getUrl(@RequestBody String longUrl){   // Adding request body directs spring to parse the request body to get the value for longURL
        return urlService.shortenUrl(longUrl);              // calls using the injected instance above
    }

    @GetMapping("/{shortKey}")    // The braces tell spring that the URL is a variable
    public String giveUrl(@PathVariable String shortKey){
        return urlService.getLongUrl(shortKey);
    }
}
