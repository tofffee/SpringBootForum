package com.example.springforumapp.auth.models.dto.registration;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class RegisterRequestDTO {
    @Size(min = 4 ,max = 15, message = "User error (username must be > 4 and < 15)")
    private String username;

    @Column(name = "email")
    private String email;

    @Size(min = 5 , message = "User error (Password must contain more than 5 symbols)")
    private String password;


    public RegisterRequestDTO() { }

    public RegisterRequestDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
