package com.learning.seckill.redis.prefix;

public class UserPrefix extends BasePrefix {
    private UserPrefix(String prefix) {
        super(prefix);
    }

    public static UserPrefix GET_BY_PHONE = new UserPrefix("user-phone:");

    public static UserPrefix GET_BY_NAME = new UserPrefix("user-name:");
}
