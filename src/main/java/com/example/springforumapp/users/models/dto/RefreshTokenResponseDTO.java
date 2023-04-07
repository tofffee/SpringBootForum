package com.example.springforumapp.users.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshTokenResponseDTO {
    private String jwtToken;
    private String refreshToken;
}
