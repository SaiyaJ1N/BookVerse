package com.onlinebookshop.validation;

import com.onlinebookshop.dto.user.UserRegistrationRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FieldMatchValidator
        implements ConstraintValidator<FieldMatch, UserRegistrationRequestDto> {
    private UserRegistrationRequestDto request;

    @Override
    public boolean isValid(UserRegistrationRequestDto registrationRequest,
                           ConstraintValidatorContext constraintValidatorContext) {
        return registrationRequest.getPassword()
                .equals(registrationRequest.getRepeatPassword());
    }
}
