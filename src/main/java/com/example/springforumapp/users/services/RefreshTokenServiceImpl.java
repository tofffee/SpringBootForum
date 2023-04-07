package com.example.springforumapp.users.services;

import com.example.springforumapp.users.models.domain.RefreshToken;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.repositories.RefreshTokenRepository;
import com.example.springforumapp.users.util.exceptions.RefreshTokenExpiredException;
import com.example.springforumapp.users.util.exceptions.RefreshTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService{
    private final RefreshTokenRepository refreshTokenRepository;
    private final long refreshTokenExpiredTime = 3; //60 minutes
    @Override
    public RefreshToken findByRefrtoken(String refrtoken) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByRefrtoken(refrtoken);
        if(refreshToken.isEmpty())
            throw new RefreshTokenNotFoundException("Refresh token was not found", "RefreshTokenServiceImpl.java: RefreshTokenNotFoundException.java");
        return refreshToken.get();
    }

    @Override
    public RefreshToken findByUserId(long userId) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByUserId(userId);
        if(refreshToken.isEmpty())
            throw new RefreshTokenNotFoundException("Refresh token was not found", "RefreshTokenServiceImpl.java: RefreshTokenNotFoundException.java");
        return refreshToken.get();
    }

    @Override
    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefrtoken(UUID.randomUUID().toString());
        refreshToken.setCreatedAt(LocalDateTime.now());
        refreshToken.setExpiredAt(LocalDateTime.now().plusMinutes(refreshTokenExpiredTime));
        refreshToken.setUser(user);

        refreshTokenRepository.save(refreshToken);

        return refreshToken;
    }

    public void verifyRefreshTokenExpiration(RefreshToken refreshToken) {
        if(refreshToken.getExpiredAt().compareTo(LocalDateTime.now()) < 0){
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenExpiredException("refresh token is expired, sign in again",
                                                   "RefreshTokenServiceImpl.java: RefreshTokenExpiredException.java");
        }
    }

    public void deleteRefreshToken(RefreshToken refreshToken){
        RefreshToken existedRefreshToken = findByRefrtoken(refreshToken.getRefrtoken());
        refreshTokenRepository.delete(existedRefreshToken);
    }
}
