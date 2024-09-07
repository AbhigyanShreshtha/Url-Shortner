package com.urlshortner.demo.service;

import com.urlshortner.demo.model.UrlMapping;
import com.urlshortner.demo.repository.UrlRepository;
import com.urlshortner.demo.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlShortenerService {

    private final UrlRepository urlRepository;
    private final ShortCodeGenerator shortCodeGenerator;

    @Autowired
    public UrlShortenerService(UrlRepository urlRepository, ShortCodeGenerator shortCodeGenerator) {
        this.urlRepository = urlRepository;
        this.shortCodeGenerator = shortCodeGenerator;
    }

    public String shortenUrl(String originalUrl) {
        if (!ValidationUtil.isValidUrl(originalUrl)) {
            throw new IllegalArgumentException("Invalid URL format");
        }

        String shortCode = shortCodeGenerator.generateShortCode(originalUrl);
        
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortCode(shortCode);
        urlMapping.setCreatedAt(LocalDateTime.now());
        
        urlRepository.save(urlMapping);
        return shortCode;
    }

    public String getOriginalUrl(String shortCode) {
        Optional<UrlMapping> mapping = urlRepository.findByShortCode(shortCode);
        return mapping.map(UrlMapping::getOriginalUrl).orElse(null);
    }
}
