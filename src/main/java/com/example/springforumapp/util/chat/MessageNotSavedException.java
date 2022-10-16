package com.example.springforumapp.util.chat;

public class MessageNotSavedException extends RuntimeException
{
    public MessageNotSavedException(String message)
    {
        super(message);
    }
}
