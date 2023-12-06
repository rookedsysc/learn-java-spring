package com.rookedsysc.springsecurity.domain.user.business;

import com.rookedsysc.springsecurity.domain.token.business.TokenBusiness;
import com.rookedsysc.springsecurity.domain.token.model.TokenResponse;
import com.rookedsysc.springsecurity.domain.user.converter.UserConverter;
import com.rookedsysc.springsecurity.domain.user.model.UserDto;
import com.rookedsysc.springsecurity.domain.user.model.UserEntity;
import com.rookedsysc.springsecurity.domain.user.model.UserRole;
import com.rookedsysc.springsecurity.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBusiness {
  private final UserConverter userConverter;
  private final UserService userService;
  private final TokenBusiness tokenBusiness;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public UserDto save(UserDto userDto) {
    // 패스워드 인코딩
    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    // UserDto -> UserEntity
    UserEntity userEntity = userConverter.toEntity(userDto);
    userEntity.setRole(UserRole.USER);
    // 이메일 중복 체크
    userService.validateDuplicatedEmail(userEntity.getEmail());
    // UserEntity 저장
    userEntity = userService.save(userEntity);
    return userConverter.toDto(userEntity);
  }

  public TokenResponse login(UserDto userDto) {
    UserEntity savedUser = userService.findByEmailOrThrow(userDto.getEmail());
    if (!passwordEncoder.matches(userDto.getPassword(), savedUser.getPassword())) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    var tokenResponse = tokenBusiness.issueToken(savedUser);

    return tokenResponse;
  }

  public List<UserDto> getAllUsers() {
    List<UserEntity> userEntities = userService.getAllUsers();
    return userConverter.toDto(userEntities);
  }
}
