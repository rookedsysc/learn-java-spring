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
  public TokenDto issueAccessToken(Long userId) {
    var data = new HashMap<String, Object>();
    data.put("userId", userId);
    return tokenHelperInterface.issueAccessToken(data);
  }

  // 리프레쉬 토큰 발급
  public TokenDto issueRefreshToken(Long userId) {
    var data = new HashMap<String, Object>();
    data.put("userId", userId);
    return tokenHelperInterface.issueRefreshToken(data);
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
