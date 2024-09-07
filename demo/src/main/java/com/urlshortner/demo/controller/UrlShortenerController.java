package com.urlshortner.demo.controller;

import com.urlshortner.demo.service.CacheDecorator;
import com.urlshortner.demo.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;
    private final CacheDecorator cacheDecorator;

    @Autowired
    public UrlShortenerController(UrlShortenerService urlShortenerService, CacheDecorator cacheDecorator) {
        this.urlShortenerService = urlShortenerService;
        this.cacheDecorator = cacheDecorator;
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestBody Map<String, String> request) {
        String originalUrl = request.get("url");
        return urlShortenerService.shortenUrl(originalUrl);
    }

    @GetMapping("/{shortCode}")
    public void redirectToOriginalUrl(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        String originalUrl = cacheDecorator.getOriginalUrlWithCache(shortCode);
        if (originalUrl != null) {
            response.sendRedirect(originalUrl);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
