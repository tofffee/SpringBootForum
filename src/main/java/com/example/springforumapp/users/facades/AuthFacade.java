package com.example.springforumapp.users.facades;

import com.example.springforumapp.users.models.dto.LoginInDTO;
import com.example.springforumapp.users.models.dto.LoginOutDTO;
import com.example.springforumapp.users.models.dto.RefreshTokenRequestDTO;
import com.example.springforumapp.users.models.dto.RefreshTokenResponseDTO;

public interface AuthFacade {
    public LoginOutDTO login(LoginInDTO loginInDTO);
    public RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
}
