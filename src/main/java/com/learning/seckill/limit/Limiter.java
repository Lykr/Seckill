package com.learning.seckill.limit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Limiter {
    private final Map<String, Bucket> limiter = new ConcurrentHashMap<>();

    public void createTokenForAllBucket() {
        for (Map.Entry<String, Bucket> entry : limiter.entrySet()) {
            Bucket bucket = entry.getValue();
            bucket.createToken();
        }
    }

    public Bucket addBucket(String bucketName, int maxTokenNum, int createTokenRate) {
        Bucket bucket = new Bucket(maxTokenNum, createTokenRate);
        limiter.put(bucketName, bucket);
        return bucket;
    }

    public Bucket getBucket(String bucketName) {
        return limiter.get(bucketName);
    }
}
