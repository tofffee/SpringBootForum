package com.example.springforumapp.users.models.dto;

public class ActivationCodeRequestDTO {
    private String code;

    public ActivationCodeRequestDTO() {
    }

    public ActivationCodeRequestDTO(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
