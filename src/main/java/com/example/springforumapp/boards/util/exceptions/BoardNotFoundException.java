package com.example.springforumapp.boards.util.exceptions;

import com.example.springforumapp.common.util.AppException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardNotFoundException extends AppException {

    public BoardNotFoundException(String message, String dbgMessage) {
        super(message, dbgMessage);
    }
}