package com.rookedsysc.springsecurity.domain.token.service;

import com.rookedsysc.springsecurity.common.error.TokenError;
import com.rookedsysc.springsecurity.common.exception.ApiException;
import com.rookedsysc.springsecurity.domain.token.ifs.TokenHelperIfs;
import com.rookedsysc.springsecurity.domain.token.model.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class TokenService {
  private final TokenHelperIfs tokenHelperInterface;

  // 토큰 발급
  public TokenDto issueAccessToken(String email) {
    return tokenHelperInterface.issueAccessToken(email);
  }

  // 리프레쉬 토큰 발급
  public TokenDto issueRefreshToken(String email) {
    return tokenHelperInterface.issueRefreshToken(email);
  }

  // 토큰 to User ID
  public Long validationToken(String token) {
    var map = tokenHelperInterface.validationTokenWithThrow(token);
    System.out.println(map.keySet());
    var userId = map.get("userId");

    Objects.requireNonNull(userId, ()-> {
      throw new ApiException(TokenError.INVALID_TOKEN);
    });

    return Long.parseLong(userId.toString());
  }
}
