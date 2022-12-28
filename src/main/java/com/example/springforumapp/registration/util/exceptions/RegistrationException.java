package com.example.springforumapp.registration.util.exceptions;

public class RegistrationException extends RuntimeException {
    private String debugMessage;
    public RegistrationException(String message, String debugMessage)
    {
        super(message);
        this.debugMessage = debugMessage;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }
}
