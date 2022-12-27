package com.example.springforumapp.auth.services;

import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.services.EmailService;
import com.example.springforumapp.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class RegistrationService {


    private final UsersService usersService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public RegistrationService(UsersService usersService, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }



    public void registerUser(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("ROLE_USER");
        user.setEnabled(false);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setAvatarUrl("http://localhost:8080/images/default_avatar.jpg");
        usersService.saveUser(user);

        String message = "Hello," + user.getUsername() + "activate your account : " + "http://localhost:8080/registration/activate/"+user.getActivationCode() ;
        emailService.send(user.getEmail(),"Activate Your account",message);
    }
}
