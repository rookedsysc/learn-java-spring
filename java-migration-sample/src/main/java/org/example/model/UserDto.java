package org.example.model;

import java.time.LocalDateTime;

public class UserDto {
  private String name;
  private Integer age;
  private String email;
  private String phoneNumber;
  private LocalDateTime registreredAt;

  public String getName() {
    return name;
  }

  public Integer getAge() {
    return age;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public LocalDateTime getRegistreredAt() {
    return registreredAt;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UserDto(String name, Integer age, String email, String phoneNumber, LocalDateTime registreredAt) {
    this.name = name;
    this.age = age;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.registreredAt = registreredAt;
  }

  public UserDto() {
  }
}
