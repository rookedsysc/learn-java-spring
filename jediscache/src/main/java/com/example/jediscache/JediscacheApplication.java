package com.example.jediscache;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class JediscacheApplication implements ApplicationRunner {

  private final UserRepository userRepository;

  public static void main(String[] args) {
    SpringApplication.run(JediscacheApplication.class, args);
  }

  @Override
	/// Application이 시작할 때 실행되는 메소드
  public void run(ApplicationArguments args) throws Exception {
    userRepository.save(User.builder().
        name("greg").
        email("greg@gmail.com")
        .build());
    userRepository.save(User.builder().
        name("tom").
        email("tom@gmail.com")
        .build());
    userRepository.save(User.builder().
        name("ryan").
        email("ryan@gmail.com")
        .build());
    userRepository.save(User.builder().
        name("bob").
        email("bob@gmail.com")
        .build());
  }
}
