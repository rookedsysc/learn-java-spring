package com.rookedsysc.springsecurity.domain.token.ifs;

import com.rookedsysc.springsecurity.domain.token.model.RefreshToken;
import com.rookedsysc.springsecurity.domain.token.model.TokenDto;

import java.util.Map;

public interface TokenHelperIfs {
  TokenDto issueAccessToken(String email);
  TokenDto issueRefreshToken(String email);

  Map<String, Object> validationTokenWithThrow(String token);
}
