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
            ApiModel<UserRegisterRequestModel> userRegisterRequest,
            BindingResult bindingResult
    ) {
        log.info(" init {}", userRegisterRequest);
        UserRegisterRequestModel body = userRegisterRequest.getData();
        if(bindingResult.hasErrors()) {
            List<String> errorMessageList = bindingResult.getFieldErrors()
                    .stream()
                    .map(
                            objectError -> {
                                String format = "%s : { %s } 은 %s";
                                return String.format(format, objectError.getField(), objectError.getRejectedValue(), objectError.getDefaultMessage());
                            })
                    .toList();

            ApiModel.Error error = ApiModel.Error.builder()
                    .errorMessage(errorMessageList)
                    .build();

            ApiModel<UserRegisterRequestModel> errorResponse = ApiModel.<UserRegisterRequestModel>builder()
                    .err(error)
                    .resultMessages(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .resultCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                    .build();

        }

        ApiModel<UserRegisterRequestModel> response = ApiModel.<UserRegisterRequestModel>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessages(HttpStatus.OK.getReasonPhrase())
                .data(body)
                .build();
        return response;
    }
}
