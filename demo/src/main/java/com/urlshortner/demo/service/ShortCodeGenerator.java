package com.urlshortner.demo.service;

import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

@Service
public class ShortCodeGenerator {

    public String generateShortCode(String originalUrl) {
        String uuid = UUID.randomUUID().toString();
        return Base64.getUrlEncoder().encodeToString(uuid.getBytes(StandardCharsets.UTF_8)).substring(0, 6);
    }
}
