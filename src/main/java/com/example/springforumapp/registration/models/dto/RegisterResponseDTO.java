package com.example.springforumapp.registration.models.dto;


public class RegisterResponseDTO {
    private String jwt;

    public RegisterResponseDTO() { }


    public RegisterResponseDTO(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
