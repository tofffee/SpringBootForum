package com.example.springforumapp.comments.util.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentException extends RuntimeException{
    private String dbgMessage;
    public CommentException(String message, String dbgMessage) {
        super(message);
        this.dbgMessage = dbgMessage;
    }
}

