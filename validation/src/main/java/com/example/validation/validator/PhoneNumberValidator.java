package com.example.validation.validator;

import com.example.validation.annotation.PhoneNumberAnnotation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// ConstraintValidator <어떠한 annotation에 적용할 것인지, 어떠한 타입이 들어올 것인지>
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberAnnotation, String> {
    private String regexp;

    @Override
    public void initialize(PhoneNumberAnnotation constraintAnnotation) {
        this.regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches(regexp);
    }
}
