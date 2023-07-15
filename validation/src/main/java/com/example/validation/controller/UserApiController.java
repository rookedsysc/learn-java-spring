package com.example.validation.controller;

import com.example.validation.model.ApiModel;
import com.example.validation.model.UserRegisterRequestModel;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserApiController {
    @PostMapping("")
    public ApiModel<UserRegisterRequestModel> register(
            @RequestBody
            @Valid
            ApiModel<UserRegisterRequestModel> userRegisterRequest
    ) {
        log.info(" init {}", userRegisterRequest);
        return userRegisterRequest;
    }
}
