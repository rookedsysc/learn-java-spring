package com.rookedsysc.springsecurity.domain.token.converter;

import com.rookedsysc.springsecurity.common.error.TokenError;
import com.rookedsysc.springsecurity.common.exception.ApiException;
import com.rookedsysc.springsecurity.domain.token.model.TokenDto;
import com.rookedsysc.springsecurity.domain.token.model.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenConverter {
  public TokenResponse toResponse(
    TokenDto accessToken,
    TokenDto refreshToken
  ) {
    Objects.requireNonNull(accessToken, () -> {throw new ApiException(TokenError.TOKEN_EXCEPTION);});
    Objects.requireNonNull(refreshToken, () -> {throw new ApiException(TokenError.TOKEN_EXCEPTION); });
    return TokenResponse.builder()
      .accessToken(accessToken.getToken())
      .accessTokenExpiredAt(accessToken.getExpiredAt())
      .refreshToken(refreshToken.getToken())
      .refreshTokenExpiredAt(refreshToken.getExpiredAt())
      .build();
  }
}
