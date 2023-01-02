package com.example.springforumapp.publications.util.exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicationException extends RuntimeException {
    private String dbgMessage;
    public PublicationException(String message, String dbgMessagee)
    {
        super(message);
        this.dbgMessage = dbgMessage;
    }
}
