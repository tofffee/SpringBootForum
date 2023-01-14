package com.example.springforumapp.email.util.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailException extends RuntimeException {
    private String dbgMessage;
    public EmailException(String message, String dbgMessage)
    {
        super(message);
        this.dbgMessage = dbgMessage;
    }
}
