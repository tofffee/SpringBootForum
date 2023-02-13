package com.example.springforumapp.users.controllers.api;


import com.example.springforumapp.users.facades.AuthFacade;
import com.example.springforumapp.users.facades.AuthFacadeImpl;
import com.example.springforumapp.users.models.dto.LoginInDTO;
import com.example.springforumapp.users.models.dto.LoginOutDTO;
import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthControllerApi {

    private final AuthFacade authFacade;

    @PostMapping()
    public ResponseEntity<ResponseApi> loginApi(@RequestBody @Valid LoginInDTO loginInDTO) {
        LoginOutDTO loginOutDTO = authFacade.login(loginInDTO);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, loginOutDTO));
    }


//    @PostMapping("/forgetPassword")
//    public ResponseEntity<ResponseApi> forgetPasswordApi(@RequestBody @Valid ForgetPasswordRequestDTO forgetPasswordRequestDTO, BindingResult bindingResult){
//        forgetPasswordValidator.validate(forgetPasswordRequestDTO, bindingResult);
//        authService.forgetPassword(forgetPasswordRequestDTO);
//        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(),"Reset password code was sent to your email"));
//    }

}
