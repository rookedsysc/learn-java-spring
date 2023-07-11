package com.example.exception.contorller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/api/b")
public class RestAPIBController {
  @GetMapping(path = "")
  public void hello() {
    throw new NumberFormatException("number format exception");
  }
  // 현재 class내의 에러에 대해서만 처리
  @ExceptionHandler(value = {NumberFormatException.class})
  public ResponseEntity<Exception> numberFormatException(
    NumberFormatException e
  ) {
    log.error("RestAPIBController", e);
    return ResponseEntity.status(200).build();
  }
}
