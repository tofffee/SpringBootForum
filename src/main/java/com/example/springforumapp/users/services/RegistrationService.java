package com.example.springforumapp.users.services;


import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.models.dto.RegisterInDTO;

public interface RegistrationService{
    public User registerUser(RegisterInDTO registerInDTO);
}
