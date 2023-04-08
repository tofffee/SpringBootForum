package com.example.springforumapp.users.services;

import com.example.springforumapp.users.models.domain.RefreshToken;
import com.example.springforumapp.users.models.domain.User;

public interface RefreshTokenService {
    public RefreshToken findByRefrtoken(String refrtoken);
    public RefreshToken findByUserId(long userId);
    public RefreshToken createRefreshToken(User user);
    public void verifyRefreshTokenExpiration(RefreshToken refreshToken);
}
