package com.rookedsysc.springsecurity.domain.token.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "refresh", timeToLive = 60 * 60 * 12)
public class RefreshToken {
  @Id
  private String email;
  private String ip;
  @Indexed
  private String refreshToken;
}
