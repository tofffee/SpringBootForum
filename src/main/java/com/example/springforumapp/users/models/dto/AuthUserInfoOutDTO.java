package com.example.springforumapp.users.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserInfoOutDTO {
    private long id;
    private String username;
    private String avatarUrl;
    private String email;
}