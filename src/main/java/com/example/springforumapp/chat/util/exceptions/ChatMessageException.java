package com.example.springforumapp.chat.util.exceptions;

import com.example.springforumapp.common.util.AppException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageException extends AppException {
    public ChatMessageException(String message, String dbgMessage) {
        super(message, dbgMessage);
    }
}
