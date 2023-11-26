package com.example.pubsub;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PublishController {
  private final RedisTemplate<String, String> redisTemplate;
  private final String key = "users:unregistered";

  @GetMapping("events/user/deregistered/{userId}")
  void publishUserDeregisteredEvent(@PathVariable String userId) {
    redisTemplate.convertAndSend(key, userId);
  }
}
