package com.example.springdataredis.controller;

import com.example.springdataredis.domain.entity.User;
import com.example.springdataredis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  @GetMapping("users/{id}")
  User getUser(@PathVariable Long id) {
    return userService.getUser(id);
  }
  @GetMapping("redishash-user/{id}")
  User getUser2(@PathVariable Long id) {
    return userService.getUser2(id);
  }
}
