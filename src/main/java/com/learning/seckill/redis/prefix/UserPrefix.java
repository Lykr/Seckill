package com.learning.seckill.redis.prefix;

public class UserPrefix extends BasePrefix {
    private UserPrefix(String prefix) {
        super(prefix, 3600L);
    }

    public static UserPrefix PHONE_TO_USER = new UserPrefix("phone2user:");
    public static UserPrefix TOKEN_TO_USER = new UserPrefix("token2user:");
}
