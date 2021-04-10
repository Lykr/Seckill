package com.learning.seckill.redis.prefix;

public class UserPrefix extends BasePrefix {
    private UserPrefix(String prefix) {
        super(prefix, 3600L);
    }

    public static UserPrefix GET_BY_PHONE = new UserPrefix("phone2user:");
    public static UserPrefix GET_BY_TOKEN = new UserPrefix("token2user:");
}
