package com.example.validation.validator;

import com.example.validation.annotation.PhoneNumberAnnotation;
import com.example.validation.annotation.YearMonthAnnotation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// ConstraintValidator <어떠한 annotation에 적용할 것인지, 어떠한 타입이 들어올 것인지>
public class YearMonthValidator implements ConstraintValidator<YearMonthAnnotation, String> {
    private String pattern;

    @Override
    public void initialize(YearMonthAnnotation constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String reValue = value + "-01";
        String rePattern = pattern + "-dd";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(rePattern);

        try {
            YearMonth.parse(reValue, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
