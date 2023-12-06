package com.rookedsysc.springsecurity.domain.token;

import com.rookedsysc.springsecurity.domain.token.business.TokenBusiness;
import com.rookedsysc.springsecurity.domain.token.model.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api/v2/token")
public class TokenOpenApiController {

  private final TokenBusiness tokenBusiness;

  @PostMapping("/refresh")
  public TokenResponse refresh(@RequestBody String token) {
    return tokenBusiness.reissueToken(token);
  }
}
