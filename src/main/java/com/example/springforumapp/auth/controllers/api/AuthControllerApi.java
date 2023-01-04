package com.example.springforumapp.auth.controllers.api;


import com.example.springforumapp.auth.models.dto.AuthResponseDTO;
import com.example.springforumapp.auth.models.dto.LoginRequestDTO;
import com.example.springforumapp.auth.models.dto.LoginResponseDTO;
import com.example.springforumapp.auth.services.AuthService;
import com.example.springforumapp.auth.util.validators.LoginValidator;
import com.example.springforumapp.errors.ApiStatus;
import com.example.springforumapp.errors.ApiSuccess;
import com.example.springforumapp.security.UserDetailsImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthControllerApi {
    private final AuthService authService;
    private final LoginValidator loginValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthControllerApi(AuthService authService, LoginValidator loginValidator, ModelMapper modelMapper) {
        this.authService = authService;
        this.loginValidator = loginValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginApi(@RequestBody @Valid LoginRequestDTO loginRequestDTO, BindingResult bindingResult) {
        loginValidator.validate(loginRequestDTO,bindingResult);
        String jwtToken = authService.authenticate(loginRequestDTO);
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(jwtToken);
        return ResponseEntity.ok(new ApiSuccess(ApiStatus.SUCCESS,HttpStatus.OK.value(),loginResponseDTO));
    }

    @GetMapping("/auth")
    public ResponseEntity<?> authUserApi(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        AuthResponseDTO authResponseDTO = modelMapper.map(userDetailsImpl.getUser(), AuthResponseDTO.class);
        return ResponseEntity.ok(new ApiSuccess(ApiStatus.SUCCESS, HttpStatus.OK.value(), authResponseDTO));
    }

}
