package com.example.springforumapp.users.util.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFoundException extends RuntimeException{
    private String dbgMessage;
    public UserNotFoundException(String message, String dbgMessage)
    {
        super(message);
        this.dbgMessage = dbgMessage;
    }
}
