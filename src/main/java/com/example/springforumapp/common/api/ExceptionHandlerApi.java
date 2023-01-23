package com.example.springforumapp.common.api;

import com.example.springforumapp.users.util.exceptions.AuthException;
import com.example.springforumapp.boards.util.exceptions.BoardException;
import com.example.springforumapp.files.util.exceptions.FileException;
import com.example.springforumapp.publications.util.exceptions.PublicationException;
import com.example.springforumapp.users.util.exceptions.RegistrationException;
import com.example.springforumapp.users.util.exceptions.ActivationProfileException;
import com.example.springforumapp.users.util.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerApi {
    @ExceptionHandler({AuthException.class})
    protected ResponseEntity<ResponseApi> handleAuthException(AuthException e) {
        ResponseErrorApi responseErrorApi = new ResponseErrorApi(
                ResponseStatusApi.FAIL,
                HttpStatus.UNAUTHORIZED.value(),
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
                HttpStatus.UNAUTHORIZED.value(),
                e.getMessage(),
                e.getDbgMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responseErrorApi, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ActivationProfileException.class})
    protected ResponseEntity<ResponseApi> handleActivationProfileException(ActivationProfileException e) {
        ResponseErrorApi responseErrorApi = new ResponseErrorApi(
                ResponseStatusApi.FAIL,
                HttpStatus.NOT_FOUND.value(),
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
                HttpStatus.NOT_FOUND.value(),
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
                HttpStatus.NOT_FOUND.value(),
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
                HttpStatus.NOT_FOUND.value(),
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
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                e.getDbgMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responseErrorApi, HttpStatus.BAD_REQUEST);
    }

}
