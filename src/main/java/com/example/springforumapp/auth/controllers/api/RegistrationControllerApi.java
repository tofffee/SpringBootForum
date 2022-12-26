package com.example.springforumapp.auth.controllers.api;

import com.example.springforumapp.auth.models.dto.registration.RegisterRequestDTO;
import com.example.springforumapp.auth.models.dto.registration.RegisterResponseDTO;
import com.example.springforumapp.auth.services.RegistrationService;
import com.example.springforumapp.auth.util.RegistrationException;
import com.example.springforumapp.auth.util.validators.RegistrationValidator;
import com.example.springforumapp.errors.ApiError;
import com.example.springforumapp.security.JWTUtil;
import com.example.springforumapp.users.models.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/register")
public class RegistrationControllerApi {

    private final RegistrationService registrationService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final RegistrationValidator registrationValidator;

    @Autowired
    public RegistrationControllerApi(RegistrationService registrationService, JWTUtil jwtUtil, AuthenticationManager authenticationManager, RegistrationValidator registrationValidator) {
        this.registrationService = registrationService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.registrationValidator = registrationValidator;
    }

    @PostMapping()
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterRequestDTO registerRequestDTO, BindingResult bindingResult){

        registrationValidator.validate(registerRequestDTO, bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errorMessage
                        .append(error.getField())
                        .append("-")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new RegistrationException(errorMessage.toString());
        }

        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setPassword(registerRequestDTO.getPassword());
        user.setEmail(registerRequestDTO.getEmail());

        registrationService.registerUser(user);

        String token = jwtUtil.generateToken(user.getUsername());
        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();
        registerResponseDTO.setJwt(token);

        return ResponseEntity.ok(registerResponseDTO);
    }

//    @ExceptionHandler
//    private ResponseEntity<ApiError> handleException(RegistrationException registrationException)
//    {
//        ApiError responseError = new ApiError(
//                registrationException.getMessage(),
//                System.currentTimeMillis()
//        );
//        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
//    }
}
