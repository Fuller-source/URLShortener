package com.example.urlshortener.service;

import com.example.urlshortener.model.UrlMapping;
import com.example.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Optional;

@Service // Allows other classes to use this service component without having to manually create a new instance of it.
public class UrlService {

    @Autowired  // Tells spring to auto find and provide an instance of the UrlRepository interface for me
                // avoids me having to write any code to connect to the database.
    private UrlRepository urlRepository;
    private static final int KEY_LENGTH = 8;

    public String shortenUrl(String longUrl) {
        int counter = 0;
        String shortKey;
        Optional<UrlMapping> existingMapping; // Represents a value that may or may not be present, to avoid NullPointerException errors.

        // DO WHILE LOOP. I HAD TO PAUSE :(
        do{
            shortKey = hashUrl(longUrl + counter);
            existingMapping = urlRepository.findByShortKey(shortKey);
            counter++;
        } while (existingMapping.isPresent());

        UrlMapping urlMapping = new UrlMapping(longUrl, shortKey);
        urlRepository.save(urlMapping);
        return shortKey;
    }

    public String getLongUrl(String shortKey) {
        return urlRepository.findByShortKey(shortKey)
                .map(UrlMapping::getLongUrl)
                .orElse(null);
    }

    private String hashUrl(String url) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); // Secure Hash Algorithm 256
            byte[] hashedBytes = digest.digest(url.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes){
                hexString.append(String.format("%02x", b));
            }

            return hexString.substring(0, KEY_LENGTH).toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algorithm not found ;_; : " + e.getMessage());
        } 
    }    
}