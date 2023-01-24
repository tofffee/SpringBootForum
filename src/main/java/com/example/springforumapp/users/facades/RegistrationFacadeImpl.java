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
import com.example.springforumapp.users.services.AuthServiceImpl;
import com.example.springforumapp.users.services.RegistrationService;
import com.example.springforumapp.users.services.RegistrationServiceImpl;
import com.example.springforumapp.users.util.exceptions.RegistrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegistrationFacadeImpl implements RegistrationFacade{

    private final RegistrationService registrationService;
    private final EmailService emailService;
    private final UserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;

    @Override
    @Transactional
    public RegisterOutDTO register(RegisterInDTO registerInDTO) throws RegistrationException, EmailException, UsernameNotFoundException {
        User user = registrationService.registerUser(registerInDTO);
        emailService.sendActivationCode(user);
        return RegisterOutDTO.builder()
                .jwt(jwtUtil.generateToken(userDetailsService.loadUserByUsername(user.getUsername())))
                .build();
    }
}
