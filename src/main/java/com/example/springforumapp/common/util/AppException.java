package com.example.springforumapp.common.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException{
    private String dbgMessage;
    public AppException(String message, String dbgMessage) {
        super(message);
        this.dbgMessage = dbgMessage;
    }
}