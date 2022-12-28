package com.example.springforumapp.auth.controllers.api;


import com.example.springforumapp.auth.models.dto.LoginRequestDTO;
import com.example.springforumapp.auth.models.dto.LoginResponseDTO;
import com.example.springforumapp.auth.services.AuthService;
import com.example.springforumapp.auth.util.validators.LoginValidator;
import com.example.springforumapp.errors.ApiSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthControllerApi {

    private final AuthService authService;
    private final LoginValidator loginValidator;

    @Autowired
    public AuthControllerApi(AuthService authService, LoginValidator loginValidator) {
        this.authService = authService;
        this.loginValidator = loginValidator;
    }


    @PostMapping()
    public ResponseEntity<?> loginApi(@RequestBody @Valid LoginRequestDTO loginRequestDTO, BindingResult bindingResult){

        loginValidator.validate(loginRequestDTO,bindingResult);

        String jwtToken = authService.authenticate(loginRequestDTO);

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setJwtToken(jwtToken);
        return ResponseEntity.ok(new ApiSuccess(HttpStatus.OK.value(),loginResponseDTO));
    }

//    @GetMapping("qwe")
//    public ResponseEntity<?> getAuthUSerInfo(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        User user = userDetails.getUser();
//        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
//        return ResponseEntity.ok(userDTO);
//    }

}
