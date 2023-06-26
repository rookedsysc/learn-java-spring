package com.example.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// RestAPI를 처리하는 Controller
@RestController
// 어떠한 주소를 받을지 지정
@RequestMapping("/api")
public class RestApiController {
    // Get Method 사용하기 위함
    @GetMapping(path = "/hello")
    public String hello(String[] args) {
        String html = "<html><body><h1>Hello Spring Boot</h1></body></html>";

        return html;
    }
}
