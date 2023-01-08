package com.example.springforumapp.files.util.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileNotSavedException extends RuntimeException{
    private String dbgMessage;
    public FileNotSavedException(String message, String dbgMessage) {
        super(message);
        this.dbgMessage = dbgMessage;
    }
}

