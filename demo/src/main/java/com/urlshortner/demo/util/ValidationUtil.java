package com.urlshortner.demo.util;

import java.util.regex.Pattern;

public class ValidationUtil {
    
    private static final String URL_REGEX = "^((https?|ftp)://)?(([a-zA-Z0-9\\.-]+)\\.([a-zA-Z]{2,6})(:[0-9]{1,5})?(/[a-zA-Z0-9\\.-]+)*/?)$";
    
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    public static boolean isValidUrl(String url) {
        if (url == null || url.isEmpty()) {
            return false;
        }
        return URL_PATTERN.matcher(url).matches();
    }
}