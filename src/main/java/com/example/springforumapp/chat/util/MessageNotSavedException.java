package com.example.springforumapp.chat.util;

public class MessageNotSavedException extends RuntimeException
{
    public MessageNotSavedException(String message)
    {
        super(message);
    }
}
