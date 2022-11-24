package com.example.springforumapp.auth.util.validators;

import com.example.springforumapp.auth.models.dto.registration.RegisterRequestDTO;
import com.example.springforumapp.auth.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegistrationValidator implements Validator {
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationValidator(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterRequestDTO registerRequestDTO = (RegisterRequestDTO)target;
        if (registrationService.checkUsernameIsAlreadyTaken(registerRequestDTO.getUsername()))
            errors.rejectValue("username","404","User with such username is already registered.");
    }
}
