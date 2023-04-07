package com.example.springforumapp.users.util.exceptions;

import com.example.springforumapp.common.util.AppException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenNotFoundException extends AppException {
    public RefreshTokenNotFoundException(String message, String dbgMessage) {
        super(message, dbgMessage);
    }
}
