package com.example.springforumapp.auth.controllers.api;

import com.example.springforumapp.auth.models.dto.registration.RegisterRequestDTO;
import com.example.springforumapp.auth.models.dto.registration.RegisterResponseDTO;
import com.example.springforumapp.auth.services.RegistrationService;
import com.example.springforumapp.auth.util.validators.RegistrationValidator;
import com.example.springforumapp.errors.ApiSuccess;
import com.example.springforumapp.security.JWTUtil;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.services.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/register")
public class RegistrationControllerApi {

    private final RegistrationService registrationService;
    private final EmailService emailService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final RegistrationValidator registrationValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public RegistrationControllerApi(RegistrationService registrationService, EmailService emailService, JWTUtil jwtUtil, AuthenticationManager authenticationManager, RegistrationValidator registrationValidator, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.emailService = emailService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.registrationValidator = registrationValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterRequestDTO registerRequestDTO, BindingResult bindingResult){

        registrationValidator.validate(registerRequestDTO, bindingResult);

        User user = modelMapper.map(registerRequestDTO, User.class);
        registrationService.registerUser(user);

        String token = jwtUtil.generateToken(user.getUsername());
        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();
        registerResponseDTO.setJwt(token);

        return ResponseEntity.ok(new ApiSuccess(HttpStatus.OK.value(),registerResponseDTO));
    }

}
