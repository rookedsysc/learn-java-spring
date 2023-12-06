package com.rookedsysc.springsecurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rookedsysc.springsecurity.domain.token.model.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {
  private final ObjectMapper objectMapper;

  @Bean
  RedisTemplate<String, RefreshToken> userRedisTemplate(RedisConnectionFactory connectionFactory) {
    var template = new RedisTemplate<String, RefreshToken>();
    template.setConnectionFactory(connectionFactory);
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new Jackson2JsonRedisSerializer<>(objectMapper, RefreshToken.class));

    return template;
  }
}