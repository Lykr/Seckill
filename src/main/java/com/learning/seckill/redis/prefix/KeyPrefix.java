package com.learning.seckill.redis.prefix;

public interface KeyPrefix {
    String getPrefix();

    Long getExpire();
}
