package com.example.springforumapp.comments.util.validators;

import com.example.springforumapp.boards.models.dto.BoardInputDTO;
import com.example.springforumapp.boards.util.exceptions.BoardException;
import com.example.springforumapp.comments.models.dto.CommentInputDTO;
import com.example.springforumapp.comments.util.exceptions.CommentException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import java.util.List;

@Component
@AllArgsConstructor
public class CommentValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CommentInputDTO.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) throws CommentException {
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
            throw new CommentException(errorMessage.toString(),"CommentValidator.java: CommentException");
        }
    }
}