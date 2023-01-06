package com.example.springforumapp.auth.models.dto;

import lombok.Data;

@Data
public class ForgetPasswordRequestDTO {
    private String userNameOrEmail;
}
