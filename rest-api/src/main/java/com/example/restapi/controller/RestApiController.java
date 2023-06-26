package com.example.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

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

    @GetMapping(path = "/echo/{message}")
    public String echo(
            // 위에 쓰여진 {}에 들어간 파라미터 명이랑 동일한 이름이어야 한다.
            @PathVariable String message
    ) {
        System.out.println("echo message : " + message);
        return message;
    }

    @GetMapping(path = "/echo_upper/{message}")
    public String echo_upper(
            // 위에 {}에 쓰여진 이름과 다를 경우 name=""을 통해 어디에 매칭 시켜줄지 정할 수 있다.
            @PathVariable(name = "message") String msg
    ) {
        System.out.println("echo message : " + msg);
        return msg.toUpperCase();
    }
}
