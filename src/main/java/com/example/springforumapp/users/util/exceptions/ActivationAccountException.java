package com.example.springforumapp.users.util.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivationAccountException extends RuntimeException{
    private String dbgMessage;

    public ActivationAccountException(String message, String dbgMessagee)
    {
        super(message);
        this.dbgMessage = dbgMessage;
    }
}
