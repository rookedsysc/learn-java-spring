package com.example.restapi.controller;

import com.example.restapi.model.BookRequest;
import com.example.restapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class PostApiController {
    @PostMapping(path = "/post")
    public BookRequest post(
            // Post는 기본적으로 객체를 받아야함
            // RequestBody : HTTP Body로 들어오는 데이터를 해당 객체에 Mapping을 해주겠다는 얘기
            @RequestBody BookRequest bookRequest
            ){
        System.out.println(bookRequest);
        return bookRequest;
    }

    @PostMapping(path = "user")
    public User getUser(
            @RequestBody User user
    ) {
        System.out.println(user);
        return user;
    }
}

