package com.learning.seckill.redis;

import com.learning.seckill.redis.prefix.KeyPrefix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    Logger log = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        String realKey = prefix.getPrefix() + key;
        log.info("Get [{}] key from Redis.", realKey);
        return (T) redisTemplate.opsForValue().get(realKey);
    }

    public void set(KeyPrefix prefix, String key, Object obj) {
        String realKey = prefix.getPrefix() + key;
        Long expire = prefix.getExpire();
        log.info("Set [{}: {}] key-value with expire [{}] to Redis.", realKey, obj.toString(), expire);
        if (expire > 0) redisTemplate.opsForValue().set(realKey, obj, expire, TimeUnit.SECONDS);
        else redisTemplate.opsForValue().set(realKey, obj);
    }

    public Boolean del(KeyPrefix prefix, String key) {
        String realKey = prefix.getPrefix() + key;
        log.info("Del [{}] key from Redis.", realKey);
        return redisTemplate.delete(realKey);
    }

    public Boolean exist(KeyPrefix prefix, String key) {
        String realKey = prefix.getPrefix() + key;
        log.info("Check if key [{}] exist", realKey);
        return redisTemplate.hasKey(realKey);
    }

    public Long getAndDecr(KeyPrefix prefix, String key) {
        String realKey = prefix.getPrefix() + key;
        log.info("Get and Decr key [{}]", realKey);
        return redisTemplate.opsForValue().decrement(realKey);
    }
}
