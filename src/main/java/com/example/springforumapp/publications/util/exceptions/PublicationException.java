package com.example.springforumapp.publications.util.exceptions;

public class PublicationException extends RuntimeException {
    private String debugMessage;
    public PublicationException(String message, String debugMessage)
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
