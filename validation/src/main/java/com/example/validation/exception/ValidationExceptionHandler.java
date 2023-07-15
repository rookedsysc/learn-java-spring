package com.example.validation.exception;

import com.example.validation.model.ApiModel;
import com.example.validation.model.UserRegisterRequestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.Binding;
import java.util.List;
// MethodArgumentNotValidException 에러 핸들러

@Slf4j
@RestControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiModel<UserRegisterRequestModel>> validexception(
            MethodArgumentNotValidException exception
    ) {
        log.error("", exception);

        List<String> errorMessageList = exception.getFieldErrors()
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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}