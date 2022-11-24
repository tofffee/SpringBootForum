package com.example.springforumapp.auth.util.validators;

import com.example.springforumapp.auth.models.dto.login.LoginRequestDTO;
import com.example.springforumapp.users.services.UsersService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
        if (!usersService.checkUserExists(loginRequestDTO.getUsername()))
            errors.rejectValue("username","404","User with such username is not registered.");
    }
}
