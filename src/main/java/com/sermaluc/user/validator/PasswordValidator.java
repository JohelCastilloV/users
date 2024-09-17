package com.sermaluc.user.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<PasswordPattern, String> {
    @Value("${validation.password.pattern}")
    private String pattern;
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return Pattern.compile(pattern).matcher(password).matches();
    }
}
