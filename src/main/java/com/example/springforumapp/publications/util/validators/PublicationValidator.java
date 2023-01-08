package com.example.springforumapp.publications.util.validators;

import com.example.springforumapp.auth.models.dto.LoginRequestDTO;
import com.example.springforumapp.auth.util.exceptions.AuthException;
import com.example.springforumapp.publications.models.dto.PublicationInputDTO;
import com.example.springforumapp.publications.util.exceptions.PublicationException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class PublicationValidator implements Validator {
    public PublicationValidator() { }
    @Override
    public boolean supports(Class<?> clazz) {
        return PublicationInputDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) throws PublicationException {
        if (errors.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> fieldErrorsList = errors.getFieldErrors();
            for(FieldError error : fieldErrorsList){
                errorMessage
                        .append(error.getField())
                        .append("-")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new PublicationException(errorMessage.toString(),"PublicationValidator.java: PublicationException");
        }
    }
}
