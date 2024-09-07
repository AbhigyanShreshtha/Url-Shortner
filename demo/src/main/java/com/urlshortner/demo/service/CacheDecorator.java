package com.urlshortner.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheDecorator {

    private final UrlShortenerService urlShortenerService;
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public CacheDecorator(UrlShortenerService urlShortenerService, RedisTemplate<String, String> redisTemplate) {
        this.urlShortenerService = urlShortenerService;
        this.redisTemplate = redisTemplate;
    }

    public String getOriginalUrlWithCache(String shortCode) {
        // Check cache
        String cachedUrl = redisTemplate.opsForValue().get(shortCode);
        if (cachedUrl != null) {
            return cachedUrl;
        }

        // Cache miss, get from DB
        String originalUrl = urlShortenerService.getOriginalUrl(shortCode);
        if (originalUrl != null) {
            redisTemplate.opsForValue().set(shortCode, originalUrl);
        }
        return originalUrl;
    }
}
