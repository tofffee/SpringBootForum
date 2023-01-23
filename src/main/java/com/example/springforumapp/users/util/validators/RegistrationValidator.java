package com.example.springforumapp.users.util.validators;

import com.example.springforumapp.users.models.dto.RegisterInDTO;
import com.example.springforumapp.users.util.exceptions.RegistrationException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class RegistrationValidator implements Validator {
    public RegistrationValidator() {}
    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterInDTO.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) throws RegistrationException {
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
            throw new RegistrationException(errorMessage.toString(),"RegistrationValidator.java: RegistrationException");
        }
    }
}
