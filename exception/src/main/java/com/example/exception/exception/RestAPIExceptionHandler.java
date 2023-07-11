package com.example.exception.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exception.contorller.RestAPIController;
import com.example.exception.model.UserApi;
import com.example.exception.model.UserResponse;
import com.example.exception.request.RestAPIExceptionCController;

import lombok.extern.slf4j.Slf4j;

// RestAPI가 사용하는 곳에 예외가 일어나면 감지하게 된다
@Slf4j
@RestControllerAdvice(basePackages = {
    "com.example.exception.controller" }, basePackageClasses = { RestAPIExceptionCController.class, RestAPIController.class })
public class RestAPIExceptionHandler {
  // 모든 에러에 대해서 처리하고 싶을 때
  // 만약 개별로 따로 지정해주면 여기서는 안잡히고 다른 곳에서 잡힌다
  @ExceptionHandler(value = { Exception.class })
  public ResponseEntity<Exception> exception(
      Exception e) {
    log.error("RestApiExceptionHandler", e);

    return ResponseEntity.status(200).build();
  }

  // 특정 에러에 대해서만 처리하고 싶을 때
  @ExceptionHandler(value = { IndexOutOfBoundsException.class })
  public ResponseEntity<Exception> outOfBound(
      Exception e) {
    log.error("IndexOutOfBoundsException", e);

    return ResponseEntity.status(200).build();
  }

  @ExceptionHandler(value = { NoSuchElementException.class })
  public ResponseEntity noSuchElement(
      NoSuchElementException e) {
    log.error("NoSuchElementException", e);

    var reponse = UserApi.builder().resultMessage(HttpStatus.NOT_FOUND.name()).resultCode(String.valueOf(HttpStatus.NOT_FOUND)).build();
    
    // UserApi를 통해서 Reponse를 보내게되면 HttpStatus는 200이 되기 때문에
    // Reponse Entity로 한 번 감싸주고 상태코드를 404로 바꿔준다
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reponse);
  }
}
