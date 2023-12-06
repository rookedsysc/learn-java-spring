package com.rookedsysc.springsecurity.domain.token.service;

import com.rookedsysc.springsecurity.common.error.TokenError;
import com.rookedsysc.springsecurity.common.exception.ApiException;
import com.rookedsysc.springsecurity.common.utils.IpUtil;
import com.rookedsysc.springsecurity.domain.token.ifs.TokenHelperIfs;
import com.rookedsysc.springsecurity.domain.token.model.RefreshToken;
import com.rookedsysc.springsecurity.domain.token.model.TokenDto;
import com.rookedsysc.springsecurity.domain.token.repository.RefreshTokenRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {
  private final TokenHelperIfs tokenHelper;
  private final RefreshTokenRedisRepository refreshTokenRedisRepository;

  // 토큰 발급
  public TokenDto issueAccessToken(String email) {
    return tokenHelper.issueAccessToken(email);
  }

  // 리프레쉬 토큰 발급
  public TokenDto issueRefreshToken(String email) {
    return tokenHelper.issueRefreshToken(email);
  }

  public void refreshTokenSave(String email, String token) {
    RefreshToken refreshToken = RefreshToken.builder()
        .email(email)
        .ip(IpUtil.getIpAddr())
        .refreshToken(token)
        .build();

    refreshTokenRedisRepository.save(refreshToken);
  }

  public RefreshToken getRefreshTokenOrThrow(String refreshToken) {
    return refreshTokenRedisRepository.findRefreshTokenByRefreshToken(refreshToken)
        .orElseThrow(
            () -> new ApiException(TokenError.INVALID_TOKEN)
        );
  }

  public void validationTokenOrThrow(String token) {
    tokenHelper.validationTokenWithThrow(token);
  }
}
