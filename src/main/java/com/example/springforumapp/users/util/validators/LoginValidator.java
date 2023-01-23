package com.example.springforumapp.users.util.validators;


import com.example.springforumapp.users.models.dto.LoginInDTO;
import com.example.springforumapp.users.util.exceptions.AuthException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import java.util.List;

@Component
@AllArgsConstructor
public class LoginValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return LoginInDTO.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) throws AuthException {
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
            throw new AuthException(errorMessage.toString(),"LoginValidator.java: AuthException");
        }
    }
}
