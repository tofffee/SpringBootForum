package com.example.springforumapp.users.facades;


import com.example.springforumapp.email.services.EmailService;
import com.example.springforumapp.users.models.dto.RegisterInDTO;
import com.example.springforumapp.users.models.dto.RegisterOutDTO;
import com.example.springforumapp.users.services.RegistrationService;
import com.example.springforumapp.security.JWTUtil;
import com.example.springforumapp.security.UserDetailsServiceImpl;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationFacadeImpl implements RegistrationFacade{

    private final RegistrationService registrationService;
    private final UserDetailsServiceImpl userDetailsService;
    private final UsersService usersService;
    private final EmailService emailService;
    private final JWTUtil jwtUtil;

    @Override
    public RegisterOutDTO register(RegisterInDTO registerInDTO) {
        User user = registrationService.registerUser(registerInDTO);
        usersService.addUser(user);
        emailService.sendActivationCode(user);
        return RegisterOutDTO.builder()
                .jwt(jwtUtil.generateToken(userDetailsService.loadUserByUsername(user.getUsername())))
                .build();
    }
}
