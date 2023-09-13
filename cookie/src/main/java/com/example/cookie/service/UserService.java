package com.example.cookie.service;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public void login(
      LoginRequest loginRequest,
      HttpServletResponse httpServletResponse
  ) {
    var id = loginRequest.getId();
    var pw = loginRequest.getPassword();
    var optionalUser = userRepository.findByName(id);
    if(optionalUser.isPresent()) {
      var userDto = optionalUser.get();

      if(userDto.getPassword().equals(pw)) {
        // Cookie에 해당 정보를 저장
        var cookie = new Cookie("authorization-cookie", userDto.getId());
        // 우리 회사가 naver이면 naver.com을 넣어주면 된다.
        // 해당하는 도메인에서만 Cookie를 사용할 수 있다.
        ///! 0.0.0.0:8080으로 접속하면 안된다.
        cookie.setDomain("localhost");
        // Cookie를 사용할 수 있는 경로를 지정한다.
        cookie.setPath("/");
        // 몇 초 후에 쿠키를 지우는지를 설정해줄 수 있다.
        // -1을 주면 세션이 유지되는 동안만 사용하는 것이다.
        cookie.setMaxAge(-1);

        // JS에서 임의로 Cookie를 읽을 수 없다.
        cookie.setHttpOnly(true);
        // Https에서만 Cookie를 사용할 수 있다.
        // cookie.setSecure(true);

        httpServletResponse.addCookie(cookie);
      }

    } else {
      throw new RuntimeException("User Not Found");
    }
  }
}
