package com.example.exception.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exception.model.UserApi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
// 먼저 처리되게 하고 싶으면 MIN_VALUE로 설정해주면 되고
// 가장 나중에 처리되게 하고 싶으면 MAX_VALUE로 설정해준다
@Order(value = Integer.MAX_VALUE) 
public class GlobalExceptionHandler {
  @ExceptionHandler(value = { Exception.class })
  public ResponseEntity<UserApi> exception(
    Exception e
  ) {
    var response = UserApi.builder().resultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())).resultMessage("접근이 금지된 사용자 입니다!!!").build();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}
