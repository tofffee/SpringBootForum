package com.example.springforumapp.registration.services;

import com.example.springforumapp.registration.util.exceptions.RegistrationException;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.email.EmailService;
import com.example.springforumapp.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;
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
    public void registerUser(User user) throws RegistrationException{

        if (usersService.checkIfUserExistsWithSuchUsername(user.getUsername()))
            throw new RegistrationException("Such user is registered","user has written username that was not registered");

        if (usersService.checkIfUserExistsWithSuchEmail(user.getEmail()))
            throw new RegistrationException("Such email is already registered","user has written email that is registered");

        Random random = new Random();
        int activationCode =  random.nextInt(10000);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        user.setEnabled(false);
        user.setActivationCode(String.valueOf(activationCode));
        user.setAvatarUrl("http://localhost:8080/images/default_avatar.jpg");
        usersService.saveUser(user);

        String message = "Hello, " + user.getUsername() + ", your activation code is : " + activationCode;
        emailService.send(user.getEmail(),"Activate Your account",message);
    }


}
