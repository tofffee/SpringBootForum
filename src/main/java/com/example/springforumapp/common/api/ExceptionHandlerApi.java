package com.example.springforumapp.common.api;

import com.example.springforumapp.boards.util.exceptions.BoardCreateException;
import com.example.springforumapp.boards.util.exceptions.BoardNotFoundException;
import com.example.springforumapp.chat.util.exceptions.DialogNotFoundException;
import com.example.springforumapp.common.util.AppException;
import com.example.springforumapp.publications.util.exceptions.PublicationDeleteException;
import com.example.springforumapp.publications.util.exceptions.PublicationNotFoundException;
import com.example.springforumapp.users.util.exceptions.AuthException;
import com.example.springforumapp.files.util.exceptions.FileException;
import com.example.springforumapp.users.util.exceptions.RegistrationException;
import com.example.springforumapp.users.util.exceptions.ActivationAccountException;
import com.example.springforumapp.users.util.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class ExceptionHandlerApi {

    //@Valid exception
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

    //when url params wrong
    @ExceptionHandler({IllegalArgumentException.class})
    protected ResponseEntity<ResponseApi> handleIllegalArgumentException(IllegalArgumentException e) {
        ResponseErrorApi responseErrorApi = new ResponseErrorApi(
                ResponseStatusApi.FAIL,
                "wrong url parameters",
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responseErrorApi, HttpStatus.BAD_REQUEST);
    }

    //Not found
    @ExceptionHandler({BoardNotFoundException.class,
                       UserNotFoundException.class,
                       PublicationNotFoundException.class,
                       DialogNotFoundException.class})
    protected ResponseEntity<ResponseApi> handleNotFoundException(AppException e) {
        ResponseErrorApi responseErrorApi = new ResponseErrorApi(
                ResponseStatusApi.FAIL,
                e.getMessage(),
                e.getDbgMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responseErrorApi, HttpStatus.NOT_FOUND);
    }

    //Create exception
    @ExceptionHandler({BoardCreateException.class})
    protected ResponseEntity<ResponseApi> handleCreateException(AppException e) {
        ResponseErrorApi responseErrorApi = new ResponseErrorApi(
                ResponseStatusApi.FAIL,
                e.getMessage(),
                e.getDbgMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responseErrorApi, HttpStatus.BAD_REQUEST);
    }

    //Delete exception
    @ExceptionHandler({PublicationDeleteException.class})
    protected ResponseEntity<ResponseApi> handleDeleteException(AppException e) {
        ResponseErrorApi responseErrorApi = new ResponseErrorApi(
                ResponseStatusApi.FAIL,
                e.getMessage(),
                e.getDbgMessage(),
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
