package com.example.springforumapp.users.facades;


import com.example.springforumapp.email.services.EmailService;
import com.example.springforumapp.email.services.EmailServiceImpl;
import com.example.springforumapp.email.util.exceptions.EmailException;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.security.UserDetailsServiceImpl;
import com.example.springforumapp.users.models.dto.LoginInDTO;
import com.example.springforumapp.users.models.dto.RegisterInDTO;
import com.example.springforumapp.users.models.dto.RegisterOutDTO;
import com.example.springforumapp.security.JWTUtil;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.services.*;
import com.example.springforumapp.users.util.exceptions.RegistrationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class RegistrationFacadeImpl implements RegistrationFacade{
    private final RegistrationService registrationService;
    private final EmailService emailService;

    @Override
    @Transactional
    public LoginInDTO register(RegisterInDTO registerInDTO) throws RegistrationException, EmailException, UsernameNotFoundException {
        User user = registrationService.registerUser(registerInDTO);
        emailService.sendActivationCode(user);
        return LoginInDTO.builder()
                .usernameOrEmail(registerInDTO.getUsername())
                .password(registerInDTO.getPassword())
                .build();
    }
}
