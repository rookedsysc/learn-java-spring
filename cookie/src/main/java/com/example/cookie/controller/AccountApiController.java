package com.example.cookie.controller;

import com.example.cookie.model.LoginRequest;
import com.example.cookie.service.HeaderService;
import com.example.cookie.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api/account")
@RequiredArgsConstructor
@RestController
public class AccountApiController {
  private final UserService userService;
  private final HeaderService headerService;

  @PostMapping("/login")
  public void login(
      @RequestBody LoginRequest loginRequest,
      // Spring에서 주입시켜줌
      HttpServletResponse response) {
    userService.login(loginRequest, response);
  }

  @PostMapping("/user_id")
  public String headerLogin(
      @RequestBody LoginRequest loginRequest,
      HttpServletResponse response) {
    return headerService.login(loginRequest, response);
  }
}
