package com.example.exception.contorller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestAPIController {
  @GetMapping(path = "")
  public void hello() {
    var list = List.of("hello");
    var element = list.get(1);
    log.info("{}", element);
  }
}
