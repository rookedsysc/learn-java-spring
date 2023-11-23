package com.example.springdataredis.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 30, nullable = false)
  private String name;

  @Column(length = 30, nullable = false, unique = true)
  private String email;

  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  // 마지막으로 변경된 시점을 자동으로 넣어주는 어노테이션
  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}
