package com.example.springforumapp.users.models.dto;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@Builder
public class LoginInDTO {

    @Size(min = 4, message = "username must contains minimum 4 symbols")
    @Size(max = 40, message = "username must contains maximum 40 symbols")
    private String username;

    @Size(min = 5, message = "password must contains minimum 5 symbols")
    @Size(max = 40, message = "password must contains maximum 40 symbols")
    private String password;
}
