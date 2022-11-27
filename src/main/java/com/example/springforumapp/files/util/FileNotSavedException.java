package com.example.springforumapp.files.util;

public class FileNotSavedException extends RuntimeException{
    public FileNotSavedException(String errorMessage)
    {
        super(errorMessage);
    }
}

