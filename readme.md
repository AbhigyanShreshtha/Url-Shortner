# URL Shortener Service - Java Spring Boot

## Overview
This is a simple URL shortener service built with Java Spring Boot. The service takes a long URL and converts it into a short code that can be used to redirect to the original URL. The project follows **SOLID principles** and implements several **design patterns** like Singleton, Factory, Repository, Decorator, and Cache-aside.

## Features
- Shorten long URLs.
- Redirect from a short URL to the original URL.
- Cache frequently accessed short URLs using Redis with **LFU caching**.
- MongoDB for persistent storage of the original and shortened URL mappings.

## Design Patterns Used
1. **Singleton Pattern**: Used for managing MongoDB and Redis connections.
2. **Factory Pattern**: Generates unique short codes.
3. **Repository Pattern**: Manages database operations.
4. **Decorator Pattern**: Adds caching functionality to the URL retrieval process.
5. **Cache-aside Pattern**: Ensures that the cache is updated after a database lookup.
