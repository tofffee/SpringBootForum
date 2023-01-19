package com.example.springforumapp.auth.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {
    private long id;
    private String username;
    private String avatarUrl;
    private String email;
}
