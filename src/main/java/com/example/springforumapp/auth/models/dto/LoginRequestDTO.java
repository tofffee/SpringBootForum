package com.example.springforumapp.auth.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.lang.management.LockInfo;

@Data
@Builder
public class LoginRequestDTO {
    @Size(min = 4 ,max = 85, message = "User error (username must be > 4 and < 15)")
    private String username;

    @Size(min = 5 , message = "User error (Password must contain more than 5 symbols)")
    private String password;
}
