package com.rookedsysc.springsecurity.domain.user.controller;

import com.rookedsysc.springsecurity.domain.token.model.TokenResponse;
import com.rookedsysc.springsecurity.domain.user.business.UserBusiness;
import com.rookedsysc.springsecurity.domain.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api/v1/users")
public class UserOpenApiController {

  private final UserBusiness userBusiness;

  @PostMapping("/join")
  public UserDto save(@RequestBody UserDto userDto) {
    return userBusiness.save(userDto);
  }

  @PostMapping("/login")
  public TokenResponse login(@RequestBody UserDto userDto) {
    return userBusiness.login(userDto);
  }
}
