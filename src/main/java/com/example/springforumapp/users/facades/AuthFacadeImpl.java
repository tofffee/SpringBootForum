package com.example.springforumapp.users.facades;

import com.example.springforumapp.security.JWTUtil;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.security.UserDetailsServiceImpl;
import com.example.springforumapp.users.models.dto.LoginInDTO;
import com.example.springforumapp.users.models.dto.LoginOutDTO;
import com.example.springforumapp.users.services.AuthService;
import com.example.springforumapp.users.services.AuthServiceImpl;
import com.example.springforumapp.users.util.exceptions.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthFacadeImpl implements AuthFacade{
    private final AuthService authService;
    private final UserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;
    @Override
    public LoginOutDTO login(LoginInDTO loginInDTO) throws AuthException, UsernameNotFoundException {
        authService.auth(loginInDTO);
        UserDetailsImpl userDetails = (UserDetailsImpl)userDetailsService.loadUserByUsername(loginInDTO.getUsername());
        return LoginOutDTO.builder()
                .jwtToken(jwtUtil.generateToken(userDetails))
                .build();
    }
}
