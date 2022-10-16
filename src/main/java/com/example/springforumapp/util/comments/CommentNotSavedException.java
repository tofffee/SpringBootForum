package com.example.springforumapp.util.comments;

public class CommentNotSavedException extends RuntimeException {
    public CommentNotSavedException(String message)
    {
        super(message);
    }
}
