package com.example.springforumapp.users.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class RegisterInDTO {
    @Size(min = 4 ,max = 15, message = "User error (username must be > 4 and < 15)")
    private String username;

    @Email
    private String email;

    @Size(min = 5 , message = "User error (Password must contain more than 5 symbols)")
    private String password;
}
