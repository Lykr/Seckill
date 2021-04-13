package com.learning.seckill.limit;

import com.learning.seckill.exception.SecKillException;
import com.learning.seckill.result.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class LimiterInterceptor implements HandlerInterceptor {
    @Autowired
    Limiter limiter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) return true;

        // Check if the method is annotated by @Limiter
        HandlerMethod hm = (HandlerMethod) handler;
        Method method = hm.getMethod();
        Limit annotation = method.getAnnotation(Limit.class);
        if (annotation == null) return true;

        // Get name of method and corresponding bucket
        String methodName = method.getName();
        Bucket bucket = limiter.getBucket(methodName);
        if (bucket == null) {
            int maxTokenNum = annotation.maxTokenNum();
            int createTokenRate = annotation.createTokenRate();
            bucket = limiter.addBucket(methodName, maxTokenNum, createTokenRate);
        }

        // Try to get token from the bucket
        if (!bucket.getToken()) throw new SecKillException(CodeMsg.LIMITER_TIP);

        return true;
    }
}
