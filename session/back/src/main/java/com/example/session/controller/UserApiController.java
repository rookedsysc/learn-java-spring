package com.example.session.controller;

import com.example.session.model.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

  @GetMapping("/me")
  public UserDto me (
      HttpSession httpSession
  ) {
    var userObject = httpSession.getAttribute("USER");

    // 세션이 Expired 됐을 수 있으므로 null 처리를 해줘야 한다.
    if(userObject == null) {
      return null;
    }
    var userDto = (UserDto) userObject;
    return userDto;
  }
}
