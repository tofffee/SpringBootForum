package com.example.springforumapp.boards.util.exceptions;

import com.example.springforumapp.common.util.AppException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardCreateException extends AppException {

    public BoardCreateException(String message, String dbgMessage) {
        super(message, dbgMessage);
    }
}