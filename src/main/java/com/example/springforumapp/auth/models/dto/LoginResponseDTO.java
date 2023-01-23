package com.example.springforumapp.auth.models.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String jwtToken;
}
