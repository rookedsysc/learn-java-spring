package com.rookedsysc.springsecurity.domain.user.converter;

import com.rookedsysc.springsecurity.domain.user.model.UserDto;
import com.rookedsysc.springsecurity.domain.user.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserConverter {
  public UserEntity toEntity(UserDto dto) {
    return UserEntity.builder()
        .email(dto.getEmail())
        .password(dto.getPassword())
        .build();
  }

  public UserDto toDto(UserEntity entity) {
    return UserDto.builder()
        .email(entity.getEmail())
        .password(entity.getPassword())
        .build();
  }

  public List<UserDto> toDto(List<UserEntity> entity) {
    return entity.stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }
}
