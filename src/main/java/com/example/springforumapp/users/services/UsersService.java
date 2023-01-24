package com.example.springforumapp.users.services;

import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.models.dto.*;

public interface UsersService {
    public User findById(long id);
    public User findByUsername(String username);
    public User findByEmail(String email);
    public User findByUsernameOrEmail(String usernameOrEmail);
    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);
    public AuthUserInfoOutDTO getAuthUserInfo(UserDetailsImpl userDetails);
    public void forgetPassword(ForgetPasswordInDTO forgetPasswordInDTO);
    public void activateUser(UserDetailsImpl userDetailsImpl, ActivationCodeRequestDTO activationCodeRequestDTO);
    public void deleteUser(long id);
    public void grantAdminRole(long id);
    public void ungrantAdminRole(long id);

}
