package com.fastcampus.batchcampus.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Customer {
  @Id
  private Long id;
  private String name;
  private String email;

  private LocalDateTime createdAt;
  private LocalDateTime loginAt;
  private Status status;

  public Customer(String name, String email) {
    this.name = name;
    this.email = email;
    this.createdAt = LocalDateTime.now();
    this.loginAt = LocalDateTime.now();
    this.status = Status.NORMAL;
  }

  public enum Status {
    NORMAL, DORMANT;
  }

}
