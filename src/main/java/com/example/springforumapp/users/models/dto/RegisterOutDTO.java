package com.example.springforumapp.users.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class RegisterOutDTO {
    private String jwtToken;
    private String refreshToken;
}
