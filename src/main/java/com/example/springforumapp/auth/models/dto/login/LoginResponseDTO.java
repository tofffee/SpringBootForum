package com.example.springforumapp.auth.models.dto.login;

public class LoginResponseDTO {
    private String jwtToken;

    public LoginResponseDTO() {}

    public LoginResponseDTO(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
