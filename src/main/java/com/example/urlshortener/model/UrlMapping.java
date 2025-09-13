package com.example.urlshortener.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
/*This class serves as a database entity using SPring Data JPA and
 * is the bluepring for the url_mapping table
 * that H2 will create for me automatically, thanks to the configuration in application.properties.
 */
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String longUrl;
    private String shortKey;

    // Constructors
    public UrlMapping() {
    }

    public UrlMapping(String longUrl, String shortKey) {
        this.longUrl = longUrl;
        this.shortKey = shortKey;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortKey() {
        return shortKey;
    }

    public void setShortKey(String shortKey) {
        this.shortKey = shortKey;
    }
}