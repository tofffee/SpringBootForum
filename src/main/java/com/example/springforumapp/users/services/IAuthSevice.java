package com.example.springforumapp.users.services;

import com.example.springforumapp.users.models.dto.ForgetPasswordInDTO;
import com.example.springforumapp.users.models.dto.LoginInDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAuthSevice {
        public UserDetails login(LoginInDTO loginInDTO);
        public void forgetPassword(ForgetPasswordInDTO forgetPasswordInDTO);
}
