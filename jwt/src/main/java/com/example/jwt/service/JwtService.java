package com.example.jwt.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService {
  private static String secretKey = "java11SpringBootJWTTokenIssueMethod";

  public String create(
      Map<String, Object> claims,
      LocalDateTime expireAt) {
    // JWT 토큰에 들어가는 키
    var key = Keys.hmacShaKeyFor(secretKey.getBytes());

    // LocalDateTime을 Date라는 형식으로 맞춰야 한다.
    var _expiredAt = Date.from(expireAt.atZone(ZoneId.systemDefault()).toInstant());

    // .compact()하면 String 타입 return 된다.
    return Jwts.builder().signWith(key, SignatureAlgorithm.HS256).setClaims(claims).setExpiration(_expiredAt).compact();
  }

  public void validation(String token) {
    var key = Keys.hmacShaKeyFor(secretKey.getBytes());

    var parser = Jwts.parserBuilder().setSigningKey(key).build();

    try {
      // 토큰 파싱
      var result = parser.parseClaimsJws(token);
      result.getBody().entrySet().forEach(value -> {
        log.info("key: {}, value: {}", value.getKey(), value.getValue());
      });
    } catch (Exception e) {
      if (e instanceof SignatureException) {
        throw new RuntimeException("JWT Token Not Valid Exception");
      } else if (e instanceof ExpiredJwtException) {
        throw new RuntimeException("JWT Token Expired Exception");
      } else {
        throw new RuntimeException("Unknown JWT Token Validation Error");
      }
    }
  }
}
