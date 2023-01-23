package com.example.springforumapp.boards.util.validators;

import com.example.springforumapp.boards.models.dto.BoardInputDTO;
import com.example.springforumapp.boards.util.exceptions.BoardException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import java.util.List;

@Component
@AllArgsConstructor
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BoardInputDTO.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) throws BoardException {
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
            throw new BoardException(errorMessage.toString(),"BoardValidator.java: BoardException");
        }
    }
}
