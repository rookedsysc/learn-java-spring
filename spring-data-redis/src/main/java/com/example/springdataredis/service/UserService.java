package com.example.springdataredis.service;

import com.example.springdataredis.domain.converter.UserConverter;
import com.example.springdataredis.domain.entity.RedisHashUser;
import com.example.springdataredis.domain.entity.User;
import com.example.springdataredis.domain.repository.RedisHashUserRepository;
import com.example.springdataredis.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final RedisTemplate<String, User> userRedisTemplate;
  private final RedisHashUserRepository redisHashUserRepository;
  private final UserConverter userConverter;

  public User getUser(final Long id) {
    var key = "users:%d".formatted(id);
    // 1. cache get
    var cachedUser = userRedisTemplate.opsForValue()
        .get(key);
    if (cachedUser != null) {
      return cachedUser;
    }

    // 2. else db ->
    var user = userRepository.findById(id)
        .orElseThrow();
    userRedisTemplate.opsForValue()
        .set(key, user, Duration.ofSeconds(30));

    return user;
  }

  public User getUser2(final Long id) {
    // 1. cache get
    Optional<RedisHashUser> cachedUser = redisHashUserRepository.findById(id);
    if (cachedUser.isPresent()) {
      return userConverter.redisHashUserToUser(cachedUser.get());
    }

    // 2. else db ->
    var user = userRepository.findById(id)
        .orElseThrow();
    var redisHash = userConverter.userToRedisHashUser(user);
    redisHashUserRepository.save(redisHash);

    return user;
  }
}
