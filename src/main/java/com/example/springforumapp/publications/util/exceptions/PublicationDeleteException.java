package com.example.springforumapp.publications.util.exceptions;

import com.example.springforumapp.common.util.AppException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicationDeleteException extends AppException {

    public PublicationDeleteException(String message, String dbgMessage) {
        super(message, dbgMessage);
    }
}