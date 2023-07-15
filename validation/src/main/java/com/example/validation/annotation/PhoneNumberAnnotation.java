package com.example.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// 어떠한 class로 검증할 것인지 정해줌
@Constraint(validatedBy = com.example.validation.validator.PhoneNumberValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberAnnotation {
    String message() default "핸드폰  번호 양식에 맞지 않습니다. ex) 000-0000-0000";
    String regexp() default "^\\d{3}-\\d{3,4}-\\d{4}$";
    // 안가지고 있으면 에러남
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
