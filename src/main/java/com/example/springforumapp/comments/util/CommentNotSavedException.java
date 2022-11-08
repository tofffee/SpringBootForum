package com.example.springforumapp.comments.util;

public class CommentNotSavedException extends RuntimeException {
    public CommentNotSavedException(String message)
    {
        super(message);
    }
}
