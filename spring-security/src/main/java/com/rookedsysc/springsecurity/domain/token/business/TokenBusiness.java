package com.rookedsysc.springsecurity.domain.token.business;

import com.rookedsysc.springsecurity.common.error.TokenError;
import com.rookedsysc.springsecurity.common.exception.ApiException;
import com.rookedsysc.springsecurity.common.utils.IpUtil;
import com.rookedsysc.springsecurity.domain.token.converter.TokenConverter;
import com.rookedsysc.springsecurity.domain.token.model.RefreshToken;
import com.rookedsysc.springsecurity.domain.token.model.TokenDto;
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

      TokenDto accessToken = tokenService.issueAccessToken(email);
      TokenDto refreshToken = tokenService.issueRefreshToken(email);

      tokenService.refreshTokenSave(email, refreshToken.getToken());

      return tokenConverter.toResponse(accessToken, refreshToken);
    }).orElseThrow(()-> new ApiException(TokenError.TOKEN_EXCEPTION));
  }

  public TokenResponse reissueToken(String refreshToken) {
    // 토큰 검증
    tokenService.validationTokenOrThrow(refreshToken);
    String currentIp = IpUtil.getIpAddr();
    RefreshToken refreshTokenEntity = tokenService.getRefreshTokenOrThrow(refreshToken);
    if (!currentIp.equals(refreshTokenEntity.getIp())) {
      throw new ApiException(TokenError.TOKEN_EXCEPTION);
    }

    // refresh, access token 재발급
    String email = refreshTokenEntity.getEmail();
    TokenDto accessToken = tokenService.issueAccessToken(email);
    TokenDto refreshTokenDto = tokenService.issueRefreshToken(email);
    String newToken = refreshTokenDto.getToken();
    tokenService.refreshTokenSave(email, newToken);

    return tokenConverter.toResponse(accessToken, refreshTokenDto);
  }
}
