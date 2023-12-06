package com.rookedsysc.springsecurity.domain.user.controller;

import com.rookedsysc.springsecurity.domain.user.business.UserBusiness;
import com.rookedsysc.springsecurity.domain.user.model.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "User API", description = "사용자 API")
public class UserApiController {

  private final UserBusiness userBusiness;

  @GetMapping("/all")
  public List<UserDto> getAllUsers() {
    return userBusiness.getAllUsers();
  }

  @GetMapping("/me")
  public ResponseEntity<String> writeReview(Authentication authentication) {
    return ResponseEntity.ok(authentication.getName() + "님의 리뷰 등록이 완료되었습니다.");
  }
}
