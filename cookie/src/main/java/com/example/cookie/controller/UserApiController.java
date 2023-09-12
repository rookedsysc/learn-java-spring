package com.example.cookie.controller;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserApiController {
  private final UserRepository userRepository;

  @GetMapping("/me")
  public Optional<UserDto> me(
          HttpServletRequest httpServletRequest,
          /// Annotation으로 Cookie 가져오는 방법
          @CookieValue(required = false, name = "authorization-cookie") String authorizationCookie
  ) {
    var cookies = httpServletRequest.getCookies();
    log.info("authorizationCookie : {}", authorizationCookie);
    var optionUserDto = userRepository.findById(authorizationCookie);
    return optionUserDto;

    ///# 수동으로 Cookie 가져오는 방법
    //if(cookies != null) {
    //  for (var cookie : cookies) {
    //    log.info("Key : {}, Value : {}", cookie.getName(), cookie.getValue());
    //  }
    //}
  }
}
