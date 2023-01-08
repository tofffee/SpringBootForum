package com.example.springforumapp.boards.util.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardException extends RuntimeException{
    private String dbgMessage;
    public BoardException(String message, String dbgMessage) {
        super(message);
        this.dbgMessage = dbgMessage;
    }
}
