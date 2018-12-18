package com.hanbaba.model;

import com.hanbaba.exceptions.RedisConnectionException;
import com.hanbaba.exceptions.RedisKeyNotFoundException;

import java.util.Set;

public interface RedisConnector {

    void set(String key, String value) throws RedisConnectionException;

    String get(String key) throws RedisConnectionException, RedisKeyNotFoundException;

    Set<String> keys(String pattern) throws RedisConnectionException;

    Long objectIdleTime(String key) throws RedisConnectionException, RedisKeyNotFoundException;


}
