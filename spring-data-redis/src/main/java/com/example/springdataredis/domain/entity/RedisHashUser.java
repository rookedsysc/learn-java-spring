package com.example.springdataredis.domain.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@RedisHash(value = "redishash-user", timeToLive = 30L)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RedisHashUser {
  @Id
  private Long id;
  private String name;
  @Indexed
  private String email;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
