package com.example.springforumapp.errors;

import org.springframework.http.HttpStatus;

public class ApiError {
    private int status;
    private String message;
    private String debugMessage;
    private long timestamp;

    public ApiError(int status, String message, String debugMessage, long timestamp) {
        this.status = status;
        this.message = message;
        this.debugMessage = debugMessage;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
