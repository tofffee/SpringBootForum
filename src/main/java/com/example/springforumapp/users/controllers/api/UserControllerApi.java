package com.example.springforumapp.users.controllers.api;

import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.models.dto.ActivationCodeRequestDTO;
import com.example.springforumapp.users.models.dto.ActivationCodeResponseDTO;
import com.example.springforumapp.users.models.dto.AuthUserInfoOutDTO;
import com.example.springforumapp.users.models.dto.UserDTO;
import com.example.springforumapp.users.services.UsersService;
import com.example.springforumapp.users.services.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class UserControllerApi {
    private final UsersService usersService;
    @GetMapping("/user/authuserinfo")
    public ResponseEntity<ResponseApi> getAuthUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails){
        AuthUserInfoOutDTO authUserInfoOutDTO = usersService.getAuthUserInfo(userDetails);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, authUserInfoOutDTO));
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<ResponseApi> getAuthUserInfo(@PathVariable("id") Long id){
        UserDTO userDTO = usersService.getProfile(id);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, userDTO));
    }
    @PostMapping("/activate")
    public ResponseEntity<ResponseApi> activateUser(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                                                    @RequestBody @Valid ActivationCodeRequestDTO activationCodeRequestDTO){
        usersService.activateUser(userDetailsImpl, activationCodeRequestDTO);
        ActivationCodeResponseDTO activationCodeResponseDTO = new ActivationCodeResponseDTO("Your profile was activated");
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, activationCodeResponseDTO));
    }
}
