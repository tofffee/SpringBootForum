package com.example.springforumapp.users.services;

import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.models.dto.ActivationCodeRequestDTO;
import com.example.springforumapp.users.util.exceptions.ActivationProfileException;

import java.util.Optional;

public interface IUsersService {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByUsernameOrEmail(String usernameOrEmail);
    void activateUser(UserDetailsImpl userDetailsImpl, ActivationCodeRequestDTO activationCodeRequestDTO);
    void addUser(User user);
    void deleteUser(long id);
    void grantAdminRole(long id);
    void ungrantAdminRole(long id);

}
