package com.example.springforumapp.registration.controllers.api;

import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.registration.models.dto.RegisterRequestDTO;
import com.example.springforumapp.registration.models.dto.RegisterResponseDTO;
import com.example.springforumapp.registration.services.RegistrationService;
import com.example.springforumapp.registration.util.validators.RegistrationValidator;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.security.JWTUtil;
import com.example.springforumapp.users.models.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class RegistrationControllerApi {
    private final RegistrationService registrationService;
    private final JWTUtil jwtUtil;
    private final RegistrationValidator registrationValidator;
    private final ModelMapper modelMapper;
    @Autowired
    public RegistrationControllerApi(RegistrationService registrationService, JWTUtil jwtUtil, RegistrationValidator registrationValidator, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.jwtUtil = jwtUtil;
        this.registrationValidator = registrationValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseApi> registerUserApi(@RequestBody @Valid RegisterRequestDTO registerRequestDTO, BindingResult bindingResult){
        registrationValidator.validate(registerRequestDTO, bindingResult);

        User user = modelMapper.map(registerRequestDTO, User.class);
        registrationService.registerUser(user);

        String token = jwtUtil.generateToken(user.getUsername());
        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO(token);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(),registerResponseDTO));
    }

    @PostMapping("/admin/register")
    public ResponseEntity<ResponseApi> registerAdminApi(@RequestBody @Valid RegisterRequestDTO registerRequestDTO, BindingResult bindingResult){

        User user = modelMapper.map(registerRequestDTO, User.class);
        registrationService.registerAdmin(user);

        String token = jwtUtil.generateToken(user.getUsername());
        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO(token);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(),registerResponseDTO));
    }

}
