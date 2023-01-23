package com.example.springforumapp.users.facades;

import com.example.springforumapp.users.models.dto.AuthOutDTO;
import com.example.springforumapp.users.models.dto.LoginInDTO;
import com.example.springforumapp.users.models.dto.LoginOutDTO;
import com.example.springforumapp.users.services.AuthService;
import com.example.springforumapp.security.JWTUtil;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.security.UserDetailsServiceImpl;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthFacadeImpl implements AuthFacade{
    private final AuthService authService;
    private final UserDetailsServiceImpl userDetailsService;
    private final UsersService usersService;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;

    @Override
    public LoginOutDTO login(LoginInDTO loginInDTO) {
        UserDetailsImpl userDetails = authService.login(loginInDTO);
        return LoginOutDTO.builder()
                .jwtToken(jwtUtil.generateToken(userDetails))
                .build();
    }

    @Override
    public AuthOutDTO auth(UserDetailsImpl userDetails) {
        User user = usersService.findByUsername(userDetails.getUsername());
        return modelMapper.map(user, AuthOutDTO.class);
    }
}
