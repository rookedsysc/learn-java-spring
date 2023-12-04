package com.rookedsysc.springsecurity.domain.token.helper;

import com.rookedsysc.springsecurity.common.exception.ApiException;
import com.rookedsysc.springsecurity.common.error.TokenError;
import com.rookedsysc.springsecurity.domain.token.ifs.TokenHelperIfs;
import com.rookedsysc.springsecurity.domain.token.model.TokenDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenHelper implements TokenHelperIfs {
  // Spring.bean.factory.annotation.Value
  @Value("${token.secret.key}")
  private String secretKey;
  @Value("${token.access-token.plus-hour}")
  private Long accessTokenPlushHour;
  @Value("${token.refresh-token.plus-hour}")
  private Long refreshTokenPlusHour;


  @Override
  public TokenDto issueAccessToken(Map<String, Object> data) {
    var expiredAtLocalDateTime = LocalDateTime.now()
        .plusHours(accessTokenPlushHour);
    var expiredAt = Date.from(expiredAtLocalDateTime.atZone(ZoneId.systemDefault())
        .toInstant());
    var keys = Keys.hmacShaKeyFor(secretKey.getBytes());

    // 토큰 생성
    var jwtToken = Jwts.builder()
        .signWith(keys, SignatureAlgorithm.HS256)
        .setClaims(data)
        .setExpiration(expiredAt)
        .compact();

    return TokenDto.builder()
        .token(jwtToken)
        .expiredAt(expiredAtLocalDateTime)
        .build();
  }

  @Override
  public TokenDto issueRefreshToken(Map<String, Object> data) {
    var expiredAtLocalDateTime = LocalDateTime.now()
        .plusHours(refreshTokenPlusHour);
    var expiredAt = Date.from(expiredAtLocalDateTime.atZone(ZoneId.systemDefault())
        .toInstant());
    var keys = Keys.hmacShaKeyFor(secretKey.getBytes());

    // 토큰 생성
    var jwtToken = Jwts.builder()
        .signWith(keys, SignatureAlgorithm.HS256)
        .setClaims(data)
        .setExpiration(expiredAt)
        .compact();

    return TokenDto.builder()
        .token(jwtToken)
        .expiredAt(expiredAtLocalDateTime)
        .build();
  }

  @Override
  public Map<String, Object> validationTokenWithThrow(String token) {
    var key = Keys.hmacShaKeyFor(secretKey.getBytes());

    var parser = Jwts.parserBuilder()
        .setSigningKey(key)
        .build();


    try {
      var result = parser.parseClaimsJws(token);
      var tokenHash = new HashMap<String, Object>(result.getBody());
      // System.out.println("keys : " + tokenHash.keySet());
      // System.out.println("value : " + tokenHash.values());

      return tokenHash;
    } catch (Exception e) {
      if (e instanceof io.jsonwebtoken.security.SignatureException) {
        ///# 토큰 서명이 일치하지 않을 때
        throw new ApiException(TokenError.INVALID_TOKEN, e);
      } else if (e instanceof ExpiredJwtException) {
        throw new ApiException(TokenError.EXPIRED_TOKEN, e);
      } else {
        ///# 알 수 없는 에러
        throw new ApiException(TokenError.TOEKN_EXCEPTION, e);
      }
    }
  }
}
