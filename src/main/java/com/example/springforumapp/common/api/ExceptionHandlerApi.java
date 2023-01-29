package com.example.springforumapp.common.api;

import com.example.springforumapp.users.util.exceptions.AuthException;
import com.example.springforumapp.boards.util.exceptions.BoardException;
import com.example.springforumapp.files.util.exceptions.FileException;
import com.example.springforumapp.publications.util.exceptions.PublicationException;
import com.example.springforumapp.users.util.exceptions.RegistrationException;
import com.example.springforumapp.users.util.exceptions.ActivationAccountException;
import com.example.springforumapp.users.util.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionHandlerApi {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<ResponseApi> handleValidationException(MethodArgumentNotValidException e) {
        StringBuilder errorMessage = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(fieldError ->
                errorMessage
                        .append(fieldError.getField())
                        .append("-")
                        .append(fieldError.getDefaultMessage())
                        .append("; "));

        ResponseErrorApi responseErrorApi = new ResponseErrorApi(
                ResponseStatusApi.FAIL,
                errorMessage.toString(),
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responseErrorApi, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({AuthException.class})
    protected ResponseEntity<ResponseApi> handleAuthException(AuthException e) {
        ResponseErrorApi responseErrorApi = new ResponseErrorApi(
                ResponseStatusApi.FAIL,
                e.getMessage(),
                e.getDbgMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responseErrorApi, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RegistrationException.class})
    protected ResponseEntity<ResponseApi> handleRegistrationException(RegistrationException e) {
        ResponseErrorApi responseErrorApi = new ResponseErrorApi(
                ResponseStatusApi.FAIL,
                e.getMessage(),
                e.getDbgMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responseErrorApi, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ActivationAccountException.class})
    protected ResponseEntity<ResponseApi> handleActivationProfileException(ActivationAccountException e) {
        ResponseErrorApi responseErrorApi = new ResponseErrorApi(
                ResponseStatusApi.FAIL,
                e.getMessage(),
                e.getDbgMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responseErrorApi, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(PublicationException.class)
    protected ResponseEntity<ResponseApi> handlePublicationException(PublicationException e) {
        ResponseErrorApi responseErrorApi = new ResponseErrorApi(
                ResponseStatusApi.FAIL,
                e.getMessage(),
                e.getDbgMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responseErrorApi, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ResponseApi> handleUserNotFoundException(UserNotFoundException e) {
        ResponseErrorApi responseErrorApi = new ResponseErrorApi(
                ResponseStatusApi.FAIL,
                e.getMessage(),
                e.getDbgMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responseErrorApi, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BoardException.class)
    protected ResponseEntity<ResponseApi> handleBoardException(BoardException e) {
        ResponseErrorApi responseErrorApi = new ResponseErrorApi(
                ResponseStatusApi.FAIL,
                e.getMessage(),
                e.getDbgMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responseErrorApi, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileException.class)
    protected ResponseEntity<ResponseApi> handleFileException(FileException e) {
        ResponseErrorApi responseErrorApi = new ResponseErrorApi(
                ResponseStatusApi.FAIL,
                e.getMessage(),
                e.getDbgMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responseErrorApi, HttpStatus.BAD_REQUEST);
    }

}
