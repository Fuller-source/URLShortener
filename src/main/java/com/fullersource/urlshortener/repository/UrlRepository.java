package com.fullersource.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullersource.urlshortener.model.UrlMapping;

import java.util.Optional;

@Repository
/* This interface, by extending jpar, gives me methods like save(), findAll()
 * and findById(). The Long refers to the type of my primary key in the UrlMapping class.
 */
public interface UrlRepository extends JpaRepository<UrlMapping, Long> {

    Optional<UrlMapping> findByShortKey(String shortKey);
}