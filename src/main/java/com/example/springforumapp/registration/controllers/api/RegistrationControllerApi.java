package com.example.springforumapp.registration.controllers.api;

import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.email.services.EmailService;
import com.example.springforumapp.registration.models.dto.RegisterRequestDTO;
import com.example.springforumapp.registration.models.dto.RegisterResponseDTO;
import com.example.springforumapp.registration.services.RegistrationService;
import com.example.springforumapp.registration.util.validators.RegistrationValidator;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.security.JWTUtil;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.security.UserDetailsServiceImpl;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegistrationControllerApi {
    private final RegistrationService registrationService;
    private final UserDetailsServiceImpl userDetailsService;
    private final UsersService usersService;
    private final EmailService emailService;
    private final JWTUtil jwtUtil;
    private final RegistrationValidator registrationValidator;

    @PostMapping("/register")
    public ResponseEntity<ResponseApi> registerUserApi(@RequestBody @Valid RegisterRequestDTO registerRequestDTO, BindingResult bindingResult){
        registrationValidator.validate(registerRequestDTO, bindingResult);
        User user = registrationService.registerUser(registerRequestDTO);
        usersService.addUser(user);
        emailService.sendActivationCode(user);
        RegisterResponseDTO dto = new RegisterResponseDTO(jwtUtil.generateToken(userDetailsService.loadUserByUsername(user.getUsername())));
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(),dto));
    }

}
