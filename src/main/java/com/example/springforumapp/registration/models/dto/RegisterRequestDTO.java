package com.example.springforumapp.registration.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    @Size(min = 4 ,max = 15, message = "User error (username must be > 4 and < 15)")
    private String username;

    @Column(name = "email")
    @Email
    private String email;

    @Size(min = 5 , message = "User error (Password must contain more than 5 symbols)")
    private String password;
}