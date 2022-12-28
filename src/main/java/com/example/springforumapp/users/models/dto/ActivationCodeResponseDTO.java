package com.example.springforumapp.users.models.dto;

public class ActivationCodeResponseDTO {
    private String message;

    public ActivationCodeResponseDTO() {
    }

    public ActivationCodeResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
