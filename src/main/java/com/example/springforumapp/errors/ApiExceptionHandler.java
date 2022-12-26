package com.example.springforumapp.errors;

import com.example.springforumapp.auth.util.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({AuthException.class})
    protected ResponseEntity<ApiError> handleAuthException(AuthException e, WebRequest request) {
        ApiError apiError = new ApiError(
                e.getMessage(),
                e.getDebugMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }


}
