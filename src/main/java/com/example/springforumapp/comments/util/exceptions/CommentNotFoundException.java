package com.example.springforumapp.comments.util.exceptions;

import com.example.springforumapp.common.util.AppException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentNotFoundException extends AppException {

    public CommentNotFoundException(String message, String dbgMessage) {
        super(message, dbgMessage);
    }
}
