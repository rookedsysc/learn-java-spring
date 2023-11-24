package com.example.springdataredis.domain.converter;

import com.example.springdataredis.domain.entity.RedisHashUser;
import com.example.springdataredis.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {
  public RedisHashUser userToRedisHashUser(User user) {
    var redisHashUser = RedisHashUser.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .createdAt(user.getCreatedAt())
        .updatedAt(user.getUpdatedAt())
        .build();
    return redisHashUser;
  }

  public User redisHashUserToUser(RedisHashUser redisHashUser) {
    var user = User.builder()
        .id(redisHashUser.getId())
        .name(redisHashUser.getName())
        .email(redisHashUser.getEmail())
        .createdAt(redisHashUser.getCreatedAt())
        .updatedAt(redisHashUser.getUpdatedAt())
        .build();
    return user;
  }
}
