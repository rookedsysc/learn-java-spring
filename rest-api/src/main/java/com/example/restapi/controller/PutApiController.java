package com.example.restapi.controller;

import com.example.restapi.model.UserRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class PutApiController {
    @PutMapping(path = "/put")
    public UserRequest put (
            @RequestBody
            UserRequest user
    ) {
        // 콘솔에 출력할 때 서버의 속도를 저하 시키지만 log.info로 처리하면
        // log 자체가 Buffer가 있기 때문에 프로그램이 진행되는 속도에 크게 영향을 주지 않는다.
        log.info("Request : {}", user);
        return user;
    }
}
