package com.example.springforumapp.users.services;

import com.example.springforumapp.security.UserDetailsServiceImpl;
import com.example.springforumapp.users.models.dto.LoginInDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    public UserDetails auth(LoginInDTO loginInDTO);
}
