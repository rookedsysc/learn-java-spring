package com.example.session.service;

import com.example.session.db.UserRepository;
import com.example.session.model.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class UserService {
  private UserRepository userRepository;

  public void login(LoginRequest loginRequest, HttpSession httpSession) {
    var id = loginRequest.getId();
    var password = loginRequest.getPassword();

    var optionalUser = userRepository.findById(id);
    if (optionalUser.isPresent()) {
      var userDto = optionalUser.get();
      if (userDto.getPassword().equals(password)) {
        // 세선에 정보를 저장한다.
        httpSession.setAttribute("user", userDto);
      } else {
        throw new RuntimeException("Password Not Match");
      }
    } else {
      throw new RuntimeException("User Not Found");
    }
  }
}
