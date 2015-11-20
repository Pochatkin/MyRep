package com.exigenservices.lectures.tags;

/**
 * Utility class
 * Created by bolbin
 * on 07.11.2014.
 */
public class Utility {

    /**
     * Validates url string
     * @param url url string to validate
     * @return true if url is correct
     */
    public static boolean validateUrl(String url) {
        return url != null &&
                (url.startsWith("http://") || url.startsWith("https://"));
    }
}