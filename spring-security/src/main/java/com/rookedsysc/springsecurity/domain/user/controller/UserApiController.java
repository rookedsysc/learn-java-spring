package com.rookedsysc.springsecurity.domain.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "User API", description = "사용자 API")
public class UserApiController {
  @GetMapping("/login/{name}")
  public String hello(@PathVariable() String name) {
    return "Hello, " + name;
  }
}
