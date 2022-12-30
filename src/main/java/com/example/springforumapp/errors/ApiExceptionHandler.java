package com.example.springforumapp.errors;

import com.example.springforumapp.auth.util.exceptions.AuthException;
import com.example.springforumapp.publications.util.exceptions.PublicationException;
import com.example.springforumapp.registration.util.exceptions.RegistrationException;
import com.example.springforumapp.users.util.exceptions.ActivationProfileException;
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

    @ExceptionHandler({ActivationProfileException.class})
    protected ResponseEntity<ApiError> handleAuthException(ActivationProfileException e) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                e.getDebugMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(PublicationException.class)
    protected ResponseEntity<ApiError> handleAuthException(PublicationException e) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                e.getDebugMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

}
