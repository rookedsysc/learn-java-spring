package com.example.springdataredis.domain.repository;

import com.example.springdataredis.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
