package com.example.springforumapp.files.util.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageNotSavedException extends RuntimeException{
    private String dbgMessage;
    public ImageNotSavedException(String message, String dbgMessage) {
        super(message);
        this.dbgMessage = dbgMessage;
    }
}