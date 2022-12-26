package com.example.springforumapp.auth.util;

public class AuthException extends RuntimeException {
    private String debugMessage;
    public AuthException(String message, String debugMessage)
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
