package com.example.springforumapp.users.controllers.api;

import com.example.springforumapp.errors.ApiSuccess;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.models.dto.ActivationCodeRequestDTO;
import com.example.springforumapp.users.models.dto.ActivationCodeResponseDTO;
import com.example.springforumapp.users.services.UsersService;
import com.example.springforumapp.users.util.exceptions.ActivationProfileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserControllerApi {
    private final UsersService usersService;

    @Autowired
    public UserControllerApi(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activateUser(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @RequestBody @Valid ActivationCodeRequestDTO activationCodeRequestDTO){

        User user = userDetailsImpl.getUser();

        usersService.activateUser(user, activationCodeRequestDTO);

        ActivationCodeResponseDTO activationCodeResponseDTO = new ActivationCodeResponseDTO();
        activationCodeResponseDTO.setMessage("Your profile was activated");
        return ResponseEntity.ok(new ApiSuccess(HttpStatus.OK.value(), activationCodeResponseDTO));
    }
}
