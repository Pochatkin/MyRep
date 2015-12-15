package com.example.julia.uley.client.sslclientserver;

/**
 * Created by Михаил on 09.12.2015.
 */
public class singleton_example {
    private static singleton_example ourInstance = new singleton_example();

    public static singleton_example getInstance() {
        return ourInstance;
    }

    private singleton_example() {
    }
}
