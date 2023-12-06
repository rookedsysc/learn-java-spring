package com.rookedsysc.springsecurity.domain.user.service;

import com.rookedsysc.springsecurity.domain.user.model.UserDto;
import com.rookedsysc.springsecurity.domain.user.model.UserEntity;
import com.rookedsysc.springsecurity.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
  private final UserRepository userRepository;

  public UserEntity save(UserEntity userEntity) {
    return userRepository.save(userEntity);
  }

  public UserEntity findByEmailOrThrow(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("해당 이메일이 존재하지 않습니다."));
  }

  public void validateDuplicatedEmail(String email) {
    userRepository.findByEmail(email)
        .ifPresent(user -> {
          throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        });
  }

  public List<UserEntity> getAllUsers() {
    return userRepository.findAll();
  }
}
