package com.example.springdataredis;

import com.example.springdataredis.domain.entity.User;
import com.example.springdataredis.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.util.ArrayList;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
public class SpringDataRedisApplication {
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataRedisApplication.class, args);
	}
}
