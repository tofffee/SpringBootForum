package com.example.springforumapp.users.util.exceptions;

import lombok.*;


@Getter
@Setter
public class AuthException extends RuntimeException {
    private String dbgMessage;
    public AuthException(String message, String dbgMessage)
    {
        super(message);
        this.dbgMessage = dbgMessage;
    }
}
