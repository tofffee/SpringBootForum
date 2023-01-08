package com.example.springforumapp.files.util.exceptions;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FileException extends RuntimeException{
    private String dbgMessage;
    public FileException(String message, String dbgMessage) {
        super(message);
        this.dbgMessage = dbgMessage;
    }
}

