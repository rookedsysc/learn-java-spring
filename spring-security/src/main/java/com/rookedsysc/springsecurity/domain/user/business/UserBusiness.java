package com.rookedsysc.springsecurity.domain.user.business;

import com.rookedsysc.springsecurity.domain.user.converter.UserConverter;
import com.rookedsysc.springsecurity.domain.user.model.UserDto;
import com.rookedsysc.springsecurity.domain.user.model.UserEntity;
import com.rookedsysc.springsecurity.domain.user.model.UserRole;
import com.rookedsysc.springsecurity.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBusiness {
  private final UserConverter userConverter;
  private final UserService userService;
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

  public UserDto findByEmailOrThrow(String email) {
    return userConverter.toDto(userService.findByEmailOrThrow(email));
  }
}
