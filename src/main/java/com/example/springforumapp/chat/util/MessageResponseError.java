package com.example.springforumapp.chat.util;

public class MessageResponseError
{
    private String message;
    private long timestamp;

    public MessageResponseError (String message, long timestamp)
    {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
