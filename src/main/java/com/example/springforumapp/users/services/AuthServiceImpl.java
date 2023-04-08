package com.example.springforumapp.users.services;

import com.example.springforumapp.security.UserDetailsServiceImpl;
import com.example.springforumapp.users.models.dto.LoginInDTO;
import com.example.springforumapp.users.util.exceptions.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    @Override
    public UserDetails auth(LoginInDTO loginInDTO) throws AuthException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginInDTO.getUsernameOrEmail(), loginInDTO.getPassword()));
        } catch (BadCredentialsException badCredentialsException){
            throw new AuthException("Incorrect credentials","AuthService.java: AuthException");
        }
        return userDetailsService.loadUserByUsername(loginInDTO.getUsernameOrEmail());
    }

}
