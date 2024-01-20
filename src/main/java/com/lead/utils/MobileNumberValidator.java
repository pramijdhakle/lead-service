package com.lead.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MobileNumberValidator implements ConstraintValidator<MobileNumber, Long> {
    @Override
    public void initialize(MobileNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        String mobileNumberString = value.toString();
        return mobileNumberString.matches("^[6-9]\\d{9}$");
    }
}