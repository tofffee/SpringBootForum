package com.example.springforumapp.users.facades;

import com.example.springforumapp.security.JWTUtil;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.security.UserDetailsServiceImpl;
import com.example.springforumapp.users.models.domain.RefreshToken;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.models.dto.LoginInDTO;
import com.example.springforumapp.users.models.dto.LoginOutDTO;
import com.example.springforumapp.users.models.dto.RefreshTokenRequestDTO;
import com.example.springforumapp.users.models.dto.RefreshTokenResponseDTO;
import com.example.springforumapp.users.services.*;
import com.example.springforumapp.users.util.exceptions.AuthException;
import com.example.springforumapp.users.util.exceptions.RefreshTokenExpiredException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthFacadeImpl implements AuthFacade{
    private final AuthService authService;
    private final UserDetailsService userDetailsService;
    private final UsersService usersService;
    private final RefreshTokenService refreshTokenService;
    private final JWTUtil jwtUtil;
    @Override
    public LoginOutDTO login(LoginInDTO loginInDTO) throws AuthException, UsernameNotFoundException {
        UserDetailsImpl userDetails = (UserDetailsImpl)authService.auth(loginInDTO);
        User user = usersService.findByUsername(userDetails.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return LoginOutDTO.builder()
                .jwtToken(jwtUtil.generateToken(userDetails))
                .refreshToken(refreshToken.getRefrtoken())
                .build();
    }

    @Override
    public RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        RefreshToken refreshToken = refreshTokenService.findByRefrtoken(refreshTokenRequestDTO.getRefreshToken());
        refreshTokenService.verifyRefreshTokenExpiration(refreshToken);

        UserDetailsImpl userDetails = (UserDetailsImpl)userDetailsService.loadUserByUsername(refreshToken.getUser().getUsername());
        User user = usersService.findByUsername(userDetails.getUsername());

        return RefreshTokenResponseDTO.builder()
                .jwtToken(jwtUtil.generateToken(userDetails))
                .refreshToken(refreshTokenService.createRefreshToken(user).getRefrtoken())
                .build();
    }
}
