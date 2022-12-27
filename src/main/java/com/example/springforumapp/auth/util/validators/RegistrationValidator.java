package com.example.springforumapp.auth.util.validators;

import com.example.springforumapp.auth.models.dto.registration.RegisterRequestDTO;
import com.example.springforumapp.auth.services.RegistrationService;
import com.example.springforumapp.auth.util.exceptions.RegistrationException;
import com.example.springforumapp.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class RegistrationValidator implements Validator {
    private final RegistrationService registrationService;
    private final UsersService usersService;

    @Autowired
    public RegistrationValidator(RegistrationService registrationService, UsersService usersService) {
        this.registrationService = registrationService;
        this.usersService = usersService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterRequestDTO registerRequestDTO = (RegisterRequestDTO)target;

        if (errors.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> fieldErrorsList= errors.getFieldErrors();
            for(FieldError error : fieldErrorsList){
                errorMessage
                        .append(error.getField())
                        .append("-")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new RegistrationException(errorMessage.toString(),"error during @Valid binding result");
        }

        if (usersService.checkIfUserExistsForAuth(registerRequestDTO.getUsername()))
            throw new RegistrationException("Such user is registered","user has written username that was not registered");
    }
}
