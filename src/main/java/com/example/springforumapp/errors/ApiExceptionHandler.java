package com.example.springforumapp.errors;

import com.example.springforumapp.auth.util.exceptions.AuthException;
import com.example.springforumapp.auth.util.exceptions.RegistrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({AuthException.class})
    protected ResponseEntity<ApiError> handleAuthException(AuthException e) {
        ApiError apiError = new ApiError(
                HttpStatus.UNAUTHORIZED.value(),
                e.getMessage(),
                e.getDebugMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({RegistrationException.class})
    protected ResponseEntity<ApiError> handleAuthException(RegistrationException e) {
        ApiError apiError = new ApiError(
                HttpStatus.UNAUTHORIZED.value(),
                e.getMessage(),
                e.getDebugMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }


}
