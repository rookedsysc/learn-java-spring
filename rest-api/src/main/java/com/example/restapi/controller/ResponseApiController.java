package com.example.restapi.controller;

import com.example.restapi.model.UserRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.tomcat.jni.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
// 반드시 Rest API를 사용하겠다
// 응답은 반드시 JSON으로 하겠다
@Controller // <-- 원래 쓰던 방식
// @RestController
@RequestMapping("/api/v1")
public class ResponseApiController {
    // http://localhost:8080/api/v1
    @GetMapping("")
    // Request 매핑은 method를 지정안해주면 post, get, put, delete 모두 받음
    // 그래서 반드시 method를 지정해줘야 하며, path에 method의 주소도 지정할 수 있음
    // @RequestMapping(path = "", method = RequestMethod.GET)

    // Controller annotation을 사용햇을 때 
    // RequestBody를 사용해줘야 Json 형태로 데이터가 내려감
    // 만약에 이거 지정 안해주면 404 에러 뜸
    // @ResponseBody
    public ResponseEntity user() {
        var user = new UserRequest();
        user.setUserName("홍길동");
        user.setAge(10);
        user.setUserEmail("rookedsysc36@gmail.com");

        log.info("user : {}", user); 

        // status code : 기본 200 ok
        // Response Entity를 사용하면 Status code를 정해줄 수 있음
        // 다른 사용 방법 : 
        // var response = ResponseEntity.status(200).body(user);
        var response = ResponseEntity.status(HttpStatus.BAD_REQUEST).header("x-custom", "hi").body(user);

        return response;
    }
}
