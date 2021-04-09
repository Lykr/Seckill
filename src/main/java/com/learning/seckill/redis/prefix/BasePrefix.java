package com.learning.seckill.redis.prefix;

public abstract class BasePrefix implements KeyPrefix {
    protected String prefix;
    protected Long expire;

    protected BasePrefix(String prefix, Long expire) {
        this.prefix = prefix;
        this.expire = expire;
    }

    protected BasePrefix(String prefix) {
        this(prefix, -1L);
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    @Override
    public String toString() {
        return "BasePrefix{" +
                "prefix='" + prefix + '\'' +
                ", expire=" + expire +
                '}';
    }
}
