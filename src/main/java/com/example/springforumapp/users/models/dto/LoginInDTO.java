package com.example.springforumapp.users.models.dto;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@Builder
public class LoginInDTO {
    @Size(min = 4 ,max = 85, message = "User error (username must be > 4 and < 15)")
    private String username;

    @Size(min = 5 , message = "User error (Password must contain more than 5 symbols)")
    private String password;
}
