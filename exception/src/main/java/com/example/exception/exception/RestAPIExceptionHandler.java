package com.example.exception.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

// RestAPI가 사용하는 곳에 예외가 일어나면 감지하게 된다
@Slf4j
@RestControllerAdvice
public class RestAPIExceptionHandler {

  // 모든 에러에 대해서 처리하고 싶을 때
  // 만약 개별로 따로 지정해주면 여기서는 안잡히고 다른 곳에서 잡힌다
  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<Exception> exception(
    Exception e
  ) {
    log.error("RestApiExceptionHandler",e);

    return ResponseEntity.status(200).build();
  }

  // 특정 에러에 대해서만 처리하고 싶을 때
  @ExceptionHandler(value = {IndexOutOfBoundsException.class})
  public ResponseEntity<Exception> outOfBound(
    Exception e
  ) {
    log.error("IndexOutOfBoundsException",e);

    return ResponseEntity.status(200).build();
  }
}
