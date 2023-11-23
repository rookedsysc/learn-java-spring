package com.example.springdataredis.service;

import com.example.springdataredis.domain.entity.User;
import com.example.springdataredis.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  public User getUser(final Long id) {
    return userRepository.findById(id)
        .orElseThrow();
  }
}
