package com.learning.seckill.redis;

import com.learning.seckill.redis.prefix.KeyPrefix;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        String realKey = prefix.getPrefix() + key;
        return (T) redisTemplate.opsForValue().get(realKey);
    }

    public void set(KeyPrefix prefix, String key, Object obj) {
        String realKey = prefix.getPrefix() + key;
        Long expire = prefix.getExpire();;
        redisTemplate.opsForValue().set(realKey, obj, expire, TimeUnit.SECONDS);
    }

    public Boolean del(KeyPrefix prefix, String key) {
        String realKey = prefix.getPrefix() + key;
        return redisTemplate.delete(realKey);
    }
}
