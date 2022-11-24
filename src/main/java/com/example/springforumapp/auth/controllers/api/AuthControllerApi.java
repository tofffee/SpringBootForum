package com.example.springforumapp.auth.controllers.api;

import com.example.springforumapp.auth.models.dto.login.LoginRequestDTO;
import com.example.springforumapp.auth.models.dto.login.LoginResponseDTO;
import com.example.springforumapp.auth.util.AuthException;
import com.example.springforumapp.auth.util.validators.LoginValidator;
import com.example.springforumapp.errors.ResponseError;
import com.example.springforumapp.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthControllerApi {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final LoginValidator loginValidator;

    @Autowired
    public AuthControllerApi(AuthenticationManager authenticationManager, JWTUtil jwtUtil, LoginValidator loginValidator) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.loginValidator = loginValidator;
    }


    @PostMapping()
    public ResponseEntity<?> loginApi(@RequestBody @Valid LoginRequestDTO loginRequestDTO, BindingResult bindingResult) {

        loginValidator.validate(loginRequestDTO,bindingResult);
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
            throw new AuthException(errorMessage.toString());
        }

        UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        try{
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException badCredentialsException){
            throw new AuthException("Incorrect credentials");
        }

        String token = jwtUtil.generateToken(loginRequestDTO.getUsername());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setJwtToken(token);
        return ResponseEntity.ok(loginResponseDTO);
    }

    @ExceptionHandler
    private ResponseEntity<ResponseError> handleException(AuthException authException)
    {
        ResponseError responseError = new ResponseError(
                authException.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }
}
