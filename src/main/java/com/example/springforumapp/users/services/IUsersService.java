package com.example.springforumapp.users.services;

import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.models.dto.ActivationCodeRequestDTO;
import com.example.springforumapp.users.util.exceptions.ActivationProfileException;

import java.util.Optional;

public interface IUsersService {
    public User findByUsername(String username);
    public User findByEmail(String email);
    public User findByUsernameOrEmail(String usernameOrEmail);
    public void activateUser(UserDetailsImpl userDetailsImpl, ActivationCodeRequestDTO activationCodeRequestDTO);
    public void saveUser(User user);
}
