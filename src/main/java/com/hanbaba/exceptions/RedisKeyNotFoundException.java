package com.hanbaba.exceptions;

public class RedisKeyNotFoundException extends Exception {

    public RedisKeyNotFoundException(String message) {
        super(message);
    }
}