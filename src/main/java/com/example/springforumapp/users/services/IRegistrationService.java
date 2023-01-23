package com.example.springforumapp.users.services;

import com.example.springforumapp.users.models.dto.RegisterInDTO;
import com.example.springforumapp.users.models.domain.User;

public interface IRegistrationService {
    User registerUser(RegisterInDTO registerInDTO);
}
