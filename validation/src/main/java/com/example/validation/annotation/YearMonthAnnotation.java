package com.example.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = com.example.validation.validator.YearMonthValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@NotBlank // Custom한 Annotation에 다른 Annotation을 붙여도 복합적으로 동작하게 된다.
public @interface YearMonthAnnotation {
    String message() default "Year Month 양식에 맞지 않습니다. ex) 000-0000-0000";
    String pattern() default "yyyy-MM";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
