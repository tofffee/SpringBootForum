package com.example.springforumapp.errors;

public class ApiError {
    private String message;
    private String debugMessage;
    private long timestamp;

    public ApiError(String message, String debugMessage, long timestamp)
    {
        this.message = message;
        this.debugMessage = debugMessage;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
