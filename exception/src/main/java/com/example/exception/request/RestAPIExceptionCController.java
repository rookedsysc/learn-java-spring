package com.example.exception.request;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/c")
public class RestAPIExceptionCController {
  @GetMapping(path = "")
  public void hello() {
    // basePackages = {"com.example.exception.controller"} 에서 잡히지 않는다
    throw new NumberFormatException();
  }
}
