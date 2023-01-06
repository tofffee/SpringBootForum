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
    public void addUser(User user);
    public void deleteUser(int id);
    public void addAdmin(User user);
    public void grantAdminRole(int id);
    public void ungrantAdminRole(int id);

}
