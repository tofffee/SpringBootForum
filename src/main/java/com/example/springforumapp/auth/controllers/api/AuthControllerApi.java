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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthControllerApi {
    private final AuthService authService;
    private final UsersService usersService;
    private final UserDetailsServiceImpl userDetailsService;
    private final JWTUtil jwtUtil;
    private final LoginValidator loginValidator;
    private final ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntity<ResponseApi> loginApi(@RequestBody @Valid LoginRequestDTO loginRequestDTO, BindingResult bindingResult) {
        loginValidator.validate(loginRequestDTO,bindingResult);
        authService.authenticate(loginRequestDTO);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDTO.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);
        LoginResponseDTO dto = new LoginResponseDTO(jwt);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), dto));
    }

    @GetMapping("/auth")
    public ResponseEntity<ResponseApi> authUserApi(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = usersService.findByUsername(userDetails.getUsername());
        AuthResponseDTO authResponseDTO = modelMapper.map(user, AuthResponseDTO.class);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), authResponseDTO));
    }

//    @PostMapping("/forgetPassword")
//    public ResponseEntity<ResponseApi> forgetPasswordApi(@RequestBody @Valid ForgetPasswordRequestDTO forgetPasswordRequestDTO, BindingResult bindingResult){
//        forgetPasswordValidator.validate(forgetPasswordRequestDTO, bindingResult);
//        authService.forgetPassword(forgetPasswordRequestDTO);
//        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(),"Reset password code was sent to your email"));
//    }

}
