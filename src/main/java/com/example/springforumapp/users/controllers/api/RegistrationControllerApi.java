package com.example.springforumapp.users.controllers.api;

import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.users.facades.AuthFacade;
import com.example.springforumapp.users.facades.RegistrationFacade;
import com.example.springforumapp.users.facades.RegistrationFacadeImpl;
import com.example.springforumapp.users.models.dto.LoginInDTO;
import com.example.springforumapp.users.models.dto.LoginOutDTO;
import com.example.springforumapp.users.models.dto.RegisterInDTO;
import com.example.springforumapp.users.models.dto.RegisterOutDTO;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class RegistrationControllerApi {
    private final RegistrationFacade registrationFacade;
    private final AuthFacade authFacade;
    @PostMapping("/register")
    public ResponseEntity<ResponseApi> registerApi(@RequestBody @Valid RegisterInDTO registerInDTO){
        LoginInDTO loginInDTO = registrationFacade.register(registerInDTO);
        LoginOutDTO loginOutDTO = authFacade.login(loginInDTO);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, loginOutDTO));
    }

}
