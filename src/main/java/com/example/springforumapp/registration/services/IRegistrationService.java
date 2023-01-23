package com.example.springforumapp.registration.services;

import com.example.springforumapp.registration.models.dto.RegisterRequestDTO;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.models.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IRegistrationService {
    User registerUser(RegisterRequestDTO registerRequestDTO);
}
