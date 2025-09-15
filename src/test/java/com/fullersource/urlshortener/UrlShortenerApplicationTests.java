package com.fullersource.urlshortener;

import com.fullersource.urlshortener.model.UrlMapping;
import com.fullersource.urlshortener.repository.UrlRepository;
import com.fullersource.urlshortener.service.UrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UrlShortenerApplicationTests {

    @Autowired
    private UrlService urlService;

    @MockBean
    private UrlRepository urlRepository;

    private static final String LONG_URL = "https://www.google.com/search?q=url+shortener+project+java";

    @Test
    void contextLoads() {
        assertNotNull(urlService);
    }

    @Test
    void shortenUrl_ShouldReturnShortKeyAndSaveToRepository() {
        // Mock the repository to return an empty optional, simulating that the key is unique
        when(urlRepository.findByShortKey(any(String.class))).thenReturn(Optional.empty());

        // Call the shortenUrl method with a known URL
        String shortKey = urlService.shortenUrl(LONG_URL);

        // Verify that a non-null short key is returned
        assertNotNull(shortKey);
        // Verify that the save method was called on the repository exactly once
        verify(urlRepository, times(1)).save(any(UrlMapping.class));
    }

    @Test
    void getLongUrl_ShouldReturnOriginalUrl_WhenKeyExists() {
        // Create a test UrlMapping object
        String testKey = "abc12345";
        UrlMapping urlMapping = new UrlMapping(LONG_URL, testKey);

        // Mock the repository to return an Optional containing our test object
        when(urlRepository.findByShortKey(testKey)).thenReturn(Optional.of(urlMapping));

        // Call the getLongUrl method with our test key
        String retrievedUrl = urlService.getLongUrl(testKey);

        // Verify that the returned URL is the same as the original URL
        assertEquals(LONG_URL, retrievedUrl);
        // Verify that the findByShortKey method was called exactly once with the correct key
        verify(urlRepository, times(1)).findByShortKey(testKey);
    }

    @Test
    void getLongUrl_ShouldReturnNull_WhenKeyDoesNotExist() {
        // Mock the repository to return an empty Optional
        when(urlRepository.findByShortKey(any(String.class))).thenReturn(Optional.empty());

        // Call the getLongUrl method with a non-existent key
        String retrievedUrl = urlService.getLongUrl("nonexistent");

        // Verify that null is returned
        assertEquals(null, retrievedUrl);
    }
}
