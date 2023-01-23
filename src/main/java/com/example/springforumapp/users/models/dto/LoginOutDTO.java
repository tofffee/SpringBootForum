package com.example.springforumapp.users.models.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class LoginOutDTO {
    private String jwtToken;
}
