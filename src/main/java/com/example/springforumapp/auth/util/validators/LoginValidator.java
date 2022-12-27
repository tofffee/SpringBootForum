package com.example.springforumapp.auth.util.validators;

import com.example.springforumapp.auth.models.dto.login.LoginRequestDTO;
import com.example.springforumapp.auth.util.exceptions.AuthException;
import com.example.springforumapp.users.services.UsersService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class LoginValidator implements Validator {

    private final UsersService usersService;

    public LoginValidator(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return LoginRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        LoginRequestDTO loginRequestDTO = (LoginRequestDTO)target;

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
            throw new AuthException(errorMessage.toString(),"error during @Valid binding result  ");
        }

        if (!usersService.checkIfUserExistsForAuth(loginRequestDTO.getUsername()))
            throw new AuthException("Such user is not registered","user has written username that was not registered");
    }
}
