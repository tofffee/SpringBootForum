package com.example.springforumapp.users.controllers.api;


import com.example.springforumapp.users.facades.AuthFacadeImpl;
import com.example.springforumapp.users.models.dto.AuthOutDTO;
import com.example.springforumapp.users.models.dto.LoginInDTO;
import com.example.springforumapp.users.models.dto.LoginOutDTO;
import com.example.springforumapp.users.util.validators.LoginValidator;
import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
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

    private final LoginValidator loginValidator;
    private final AuthFacadeImpl authFacade;

    @PostMapping("/login")
    public ResponseEntity<ResponseApi> loginApi(@RequestBody @Valid LoginInDTO loginInDTO, BindingResult bindingResult) {
        loginValidator.validate(loginInDTO,bindingResult);
        LoginOutDTO dto = authFacade.login(loginInDTO);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), dto));
    }

    @GetMapping("/auth")
    public ResponseEntity<ResponseApi> authUserApi(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        AuthOutDTO dto = authFacade.auth(userDetails);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), dto));
    }

//    @PostMapping("/forgetPassword")
//    public ResponseEntity<ResponseApi> forgetPasswordApi(@RequestBody @Valid ForgetPasswordRequestDTO forgetPasswordRequestDTO, BindingResult bindingResult){
//        forgetPasswordValidator.validate(forgetPasswordRequestDTO, bindingResult);
//        authService.forgetPassword(forgetPasswordRequestDTO);
//        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(),"Reset password code was sent to your email"));
//    }

}
