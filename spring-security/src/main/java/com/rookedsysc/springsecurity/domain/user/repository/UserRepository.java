package com.rookedsysc.springsecurity.domain.user.repository;

import com.rookedsysc.springsecurity.domain.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  public Optional<UserEntity> findByEmail(String email);
}
