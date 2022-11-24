package com.example.springforumapp.auth.models.dto.login;

import javax.validation.constraints.Size;
import java.lang.management.LockInfo;

public class LoginRequestDTO {
    @Size(min = 4 ,max = 15, message = "User error (username must be > 4 and < 15)")
    private String username;
    @Size(min = 5 , message = "User error (Password must contain more than 5 symbols)")
    private String password;

    public LoginRequestDTO(){};

    public LoginRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
