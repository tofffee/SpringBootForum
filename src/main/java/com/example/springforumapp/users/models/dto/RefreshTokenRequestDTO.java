package com.example.springforumapp.users.models.dto;

import lombok.Builder;
import lombok.Data;


@Data
public class RefreshTokenRequestDTO {
    private String refreshToken;
}
