package com.example.springforumapp.users.util.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivationProfileException extends RuntimeException{
    private String dbgMessage;

    public ActivationProfileException(String message, String dbgMessagee)
    {
        super(message);
        this.dbgMessage = dbgMessage;
    }
}
