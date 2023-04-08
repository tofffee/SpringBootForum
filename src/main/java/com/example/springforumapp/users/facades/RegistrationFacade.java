package com.example.springforumapp.users.facades;

import com.example.springforumapp.users.models.dto.LoginInDTO;
import com.example.springforumapp.users.models.dto.RegisterInDTO;
import com.example.springforumapp.users.models.dto.RegisterOutDTO;


public interface RegistrationFacade {
    public LoginInDTO register(RegisterInDTO registerInDTO);
}
