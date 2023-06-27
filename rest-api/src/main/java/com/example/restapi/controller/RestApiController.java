package com.example.restapi.controller;

import com.example.restapi.model.BookQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Slf4j
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

    // http://localhost:8080/api/echo_upper/hi/age/500/isMan/false
    @GetMapping(path = "/echo_upper/{message}/age/{age}/isMan/{isMan}")
    public String echo_upper(
            // 위에 {}에 쓰여진 이름과 다를 경우 name=""을 통해 어디에 매칭 시켜줄지 정할 수 있다.
            @PathVariable(name = "message") String msg,
            @PathVariable int age,
            @PathVariable boolean isMan
    ) {
        System.out.println("echo message : " + msg);
        System.out.println("echo message : " + age);
        System.out.println("echo message : " + isMan);

        return msg.toUpperCase();
    }

    // http://localhost:8080/api/book?category=IT&issuedYear=2018&issued-month=01&issued_day=31
    @GetMapping(path = "/book")
    public void book(
            // Query Parameter를 지정
            @RequestParam String category,
            @RequestParam String issuedYear,
            // Query Parameter를 어떤 이름으로 전달할지 지정
            @RequestParam(name = "issued-month")String issuedMonth ,
            @RequestParam(name = "issued_day")String issuedDay
    )  {
        System.out.println(category);
        System.out.println(issuedYear);
        System.out.println(issuedMonth);
        System.out.println(issuedDay);
    }

    // http://localhost:8080/api/book2?category=IT&issuedYear=2018&issuedMonth=01&issuedDay=31
    @GetMapping(path = "/book2")
    public void book2(
            BookQueryParam bookQueryParam
    )  {
        System.out.println(bookQueryParam.toString());
    }

    // 아래와 같이 여러 개의 path를 받아줄 수도 잇음
    @DeleteMapping(path = {"/user/{userName}/delete", "/user/{userName}/del"})
    public void delete(
           @PathVariable String userName
    ) {
        log.info("user-name : {}", userName);
    }
}
