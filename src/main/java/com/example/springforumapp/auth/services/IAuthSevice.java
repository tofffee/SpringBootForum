package com.example.springforumapp.auth.services;

import com.example.springforumapp.auth.models.dto.ForgetPasswordRequestDTO;
import com.example.springforumapp.auth.models.dto.LoginRequestDTO;
import com.example.springforumapp.auth.models.dto.LoginResponseDTO;
import com.example.springforumapp.auth.util.exceptions.AuthException;
import org.springframework.http.ResponseEntity;

public interface IAuthSevice {
        public void authenticate(LoginRequestDTO loginRequestDTO);
        public void forgetPassword(ForgetPasswordRequestDTO forgetPasswordRequestDTO);
}
