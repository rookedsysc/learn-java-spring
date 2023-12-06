package com.rookedsysc.springsecurity.domain.token.business;

import com.rookedsysc.springsecurity.common.error.TokenError;
import com.rookedsysc.springsecurity.common.exception.ApiException;
import com.rookedsysc.springsecurity.domain.token.converter.TokenConverter;
import com.rookedsysc.springsecurity.domain.token.model.TokenResponse;
import com.rookedsysc.springsecurity.domain.token.service.TokenService;
import com.rookedsysc.springsecurity.domain.user.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenBusiness {
  private final TokenService tokenService;
  private final TokenConverter tokenConverter;

  /**
   * 1. User Entity id 추출
   * 2. access, refresh 발행
   * 3. converter -> token response로 변경
   */
  public TokenResponse issueToken(UserEntity userEntity) {
    return Optional.ofNullable(userEntity).map(ue -> {
      var email = ue.getEmail();

      var accessToken = tokenService.issueAccessToken(email);
      var refreshToken = tokenService.issueRefreshToken(email);

      return tokenConverter.toResponse(accessToken, refreshToken);
    }).orElseThrow(()-> new ApiException(TokenError.TOKEN_EXCEPTION));
  }

  public Long validationAccessToken(String accessToken) {
    var userId = tokenService.validationToken(accessToken);
    return userId;
  }
}
