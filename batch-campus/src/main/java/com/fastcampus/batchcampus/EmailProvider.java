package com.fastcampus.batchcampus;

import lombok.extern.slf4j.Slf4j;

public interface EmailProvider {
  void send(String email, String title, String message);

  @Slf4j
  class Fake implements EmailProvider {
    @Override
    public void send(String email, String title, String message) {
      log.info("email: {}, title: {}, message: {}", email, title, message);
    }
  }
}
