package com.example.springforumapp.users.facades;

import com.example.springforumapp.users.models.dto.LoginInDTO;
import com.example.springforumapp.users.models.dto.LoginOutDTO;

public interface AuthFacade {
    public LoginOutDTO login(LoginInDTO loginInDTO);
}
