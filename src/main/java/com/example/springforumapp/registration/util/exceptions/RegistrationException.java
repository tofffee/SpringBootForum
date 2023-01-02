package com.example.springforumapp.registration.util.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationException extends RuntimeException {
    private String dbgMessage;
    public RegistrationException(String message, String dbgMessage)
    {
        super(message);
        this.dbgMessage = dbgMessage;
    }
}
