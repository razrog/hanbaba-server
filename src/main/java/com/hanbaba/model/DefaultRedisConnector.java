package com.hanbaba.model;



import com.hanbaba.exceptions.RedisConnectionException;
import com.hanbaba.exceptions.RedisKeyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.Set;


@Component
public class DefaultRedisConnector implements RedisConnector {

    private Logger logger = LoggerFactory.getLogger(DefaultRedisConnector.class);

    private String host;

    public DefaultRedisConnector(@Value("${redis.host}") String host) {
        this.host = host;
    }

    private Jedis getClient() throws RedisConnectionException {
        try {
            return new Jedis(host);
        } catch (Exception e) {
            logger.error("Cannot create new Redis Connection!");
            throw new RedisConnectionException("Couldn't Connect to Server! [" + host + "] Exception [" + e.getMessage() + "]");
        }
    }

    @Override
    public void set(String key, String value) throws RedisConnectionException {
        try (Jedis jedis = getClient()) {
            jedis.set(key, value);
        } catch (JedisConnectionException e) {
            logger.error("Couldn't Connect to Redis Server [" + this.host + "]");
            throw new RedisConnectionException("Redis Server is unavailable [ " + this.host + " ]");
        }
    }

    @Override
    public String get(String key) throws RedisConnectionException, RedisKeyNotFoundException {
        try (Jedis jedis = getClient()) {
            return jedis.get(key);
        } catch (JedisConnectionException e) {
            logger.error("Couldn't Connect to Redis Server [" + this.host + "]");
            throw new RedisConnectionException("Redis Server is unavailable [ " + this.host + " ]");
        } catch (NullPointerException e) {
            logger.error("Redis Key not Found! [" + key + "]");
            throw new RedisKeyNotFoundException("Redis key [ " + key + " ] not found!");
        }
    }

    @Override
    public Set<String> keys(String pattern) throws RedisConnectionException {
        try (Jedis jedis = getClient()) {
            return jedis.keys(pattern);
        } catch (JedisConnectionException e) {
            logger.error("Couldn't Connect to Redis Server [" + this.host + "]");
            throw new RedisConnectionException("Redis Server is unavailable [ " + this.host + " ]");
        }
    }

    @Override
    public Long objectIdleTime(String key) throws RedisConnectionException, RedisKeyNotFoundException {
        try (Jedis jedis = getClient()) {
            return jedis.objectIdletime(key);
        } catch (JedisConnectionException e) {
            logger.error("Couldn't Connect to Redis Server [" + this.host + "]");
            throw new RedisConnectionException("Redis Server is unavailable [ " + this.host + " ]");
        } catch (NullPointerException e) {
            logger.error("Redis Key not Found! [" + key + "]");
            throw new RedisKeyNotFoundException("Redis key [ " + key + " ] not found!");
        }
    }
}
