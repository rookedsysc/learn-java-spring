package com.rookedsysc.springsecurity.domain.user.controller;

import com.rookedsysc.springsecurity.domain.user.business.UserBusiness;
import com.rookedsysc.springsecurity.domain.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api")
public class UserOpenApiController {

  private final UserBusiness userBusiness;

  @PostMapping("/register")
  public UserDto save(@RequestBody UserDto userDto) {
    return userBusiness.save(userDto);
  }
}
