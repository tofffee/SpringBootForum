package com.example.springforumapp.users.util.exceptions;

public class ActivationProfileException extends RuntimeException{
    private String debugMessage;

    public ActivationProfileException(String message, String debugMessage)
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
