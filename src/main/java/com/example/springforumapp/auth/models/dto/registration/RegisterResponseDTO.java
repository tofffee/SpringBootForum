package com.example.springforumapp.auth.models.dto.registration;


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
