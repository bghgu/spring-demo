package com.example.demo;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by ds on 2021/03/18.
 */

@RestController
public class TestController {

    private final RedisTemplate<String, String> redisTemplate;

    public TestController(final RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/test")
    public Object test() {
        String key = "demo-redis";
        SetOperations<String, String> stringStringSetOperations = redisTemplate.opsForSet();
        stringStringSetOperations.add(key, LocalDateTime.now().toString());
        return stringStringSetOperations.members(key);
    }
}