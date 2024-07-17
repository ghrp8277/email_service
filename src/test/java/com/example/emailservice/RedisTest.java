package com.example.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void testRedisConnection() {
        try {
            redisTemplate.opsForValue().set("testKey", "testValue");
            String value = (String) redisTemplate.opsForValue().get("testKey");
            System.out.println("Retrieved value from Redis: " + value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
