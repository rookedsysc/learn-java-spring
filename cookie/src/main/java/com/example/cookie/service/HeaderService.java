package com.example.cookie.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.LoginRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HeaderService {
  private final UserRepository userRepository;

  public String login(
      LoginRequest loginRequest,
      HttpServletResponse httpServletResponse) {
    var id = loginRequest.getId();
    var pw = loginRequest.getPassword();
    var optionalUser = userRepository.findByName(id);
    if (optionalUser.isPresent()) {
      var userDto = optionalUser.get();
      if (userDto.getPassword().equals(pw)) {
        return userDto.getId();
      }

    } else {
      throw new RuntimeException("User Not Found");
    }

    return null;
  }
}
