package com.example.springforumapp.users.facades;

import com.example.springforumapp.users.models.dto.AuthOutDTO;
import com.example.springforumapp.users.models.dto.LoginInDTO;
import com.example.springforumapp.users.models.dto.LoginOutDTO;
import com.example.springforumapp.security.UserDetailsImpl;

public interface AuthFacade {
    public LoginOutDTO login(LoginInDTO loginInDTO);
    public AuthOutDTO auth(UserDetailsImpl userDetails);
}
