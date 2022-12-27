package com.example.springforumapp.auth.services;


import com.example.springforumapp.auth.models.dto.login.LoginRequestDTO;
import com.example.springforumapp.auth.util.exceptions.AuthException;
import com.example.springforumapp.security.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;



    public AuthService(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(LoginRequestDTO loginRequestDTO) {
        UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        try{
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException badCredentialsException){
            throw new AuthException("Incorrect credentials","user has written not correct username or password");
        }
        return jwtUtil.generateToken(loginRequestDTO.getUsername());
    }
}
