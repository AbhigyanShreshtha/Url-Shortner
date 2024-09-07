package com.urlshortner.demo.repository;

import com.urlshortner.demo.model.UrlMapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UrlRepository extends MongoRepository<UrlMapping, String> {
    Optional<UrlMapping> findByShortCode(String shortCode);
}
