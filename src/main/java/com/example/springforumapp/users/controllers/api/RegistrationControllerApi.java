package com.example.springforumapp.users.controllers.api;

import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.users.facades.RegistrationFacadeImpl;
import com.example.springforumapp.users.models.dto.RegisterInDTO;
import com.example.springforumapp.users.models.dto.RegisterOutDTO;
import com.example.springforumapp.users.util.validators.RegistrationValidator;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegistrationControllerApi {
    private final RegistrationFacadeImpl registrationFacade;
    private final RegistrationValidator registrationValidator;

    @PostMapping("/register")
    public ResponseEntity<ResponseApi> registerApi(@RequestBody @Valid RegisterInDTO registerInDTO, BindingResult bindingResult){
        registrationValidator.validate(registerInDTO, bindingResult);
        RegisterOutDTO dto = registrationFacade.register(registerInDTO);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(),dto));
    }

}
