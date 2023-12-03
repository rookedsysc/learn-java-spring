package com.rookedsysc.springsecurity.domain.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class UserDto {
  private String email;

  private String password;

  public void setPassword(String password) {
    this.password = password;
  }

}
