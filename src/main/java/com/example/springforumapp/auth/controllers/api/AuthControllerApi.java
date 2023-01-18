package com.example.springforumapp.auth.controllers.api;


import com.example.springforumapp.auth.models.dto.AuthResponseDTO;
import com.example.springforumapp.auth.models.dto.ForgetPasswordRequestDTO;
import com.example.springforumapp.auth.models.dto.LoginRequestDTO;
import com.example.springforumapp.auth.models.dto.LoginResponseDTO;
import com.example.springforumapp.auth.services.AuthService;
import com.example.springforumapp.auth.util.validators.ForgetPasswordValidator;
import com.example.springforumapp.auth.util.validators.LoginValidator;
import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AuthControllerApi {
    private final AuthService authService;
    private final LoginValidator loginValidator;
    private final ForgetPasswordValidator forgetPasswordValidator;
    private final ModelMapper modelMapper; //

    @PostMapping("/login")
    public ResponseEntity<ResponseApi> loginApi(@RequestBody @Valid LoginRequestDTO loginRequestDTO, BindingResult bindingResult) {
        loginValidator.validate(loginRequestDTO,bindingResult);
        String jwtToken = authService.authenticate(loginRequestDTO);
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(jwtToken);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS,HttpStatus.OK.value(),loginResponseDTO));
    }

    @GetMapping("/auth")
    public ResponseEntity<ResponseApi> authUserApi(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        AuthResponseDTO authResponseDTO = modelMapper.map(userDetailsImpl.getUser(), AuthResponseDTO.class);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), authResponseDTO));
    }

    @PostMapping("/forgetPassword")
    public ResponseEntity<ResponseApi> forgetPasswordApi(@RequestBody @Valid ForgetPasswordRequestDTO forgetPasswordRequestDTO, BindingResult bindingResult){
        forgetPasswordValidator.validate(forgetPasswordRequestDTO, bindingResult);
        authService.forgetPassword(forgetPasswordRequestDTO);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(),"Reset password code was sent to your email"));
    }

}
