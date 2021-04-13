package com.learning.seckill.limit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableScheduling
@Configuration
public class LimiterConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(limiterInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public LimiterInterceptor limiterInterceptor() {
        return new LimiterInterceptor();
    }

    @Bean
    public Limiter limiter() {
        return new Limiter();
    }

    @Scheduled(fixedRate = 1000) // call this method per 1000ms
    public void timer() {
        limiter().createTokenForAllBucket();
    }
}
