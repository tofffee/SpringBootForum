package com.example.springforumapp.registration.services;

import com.example.springforumapp.common.util.RandomUtil;
import com.example.springforumapp.registration.util.exceptions.RegistrationException;
import com.example.springforumapp.users.models.domain.Role;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.email.EmailService;
import com.example.springforumapp.users.repositories.RolesRepository;
import com.example.springforumapp.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
public class RegistrationService implements IRegistrationService {
    private final UsersService usersService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final RandomUtil randomUtil;

    @Autowired
    public RegistrationService(UsersService usersService, EmailService emailService, PasswordEncoder passwordEncoder, RandomUtil randomUtil) {
        this.usersService = usersService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.randomUtil = randomUtil;
    }
    public void registerUser(User user) throws RegistrationException{
        if (usersService.findByUsername(user.getUsername()) != null)
            throw new RegistrationException("Such username is already taken","RegistrationService.java: RegistrationException");

        if (usersService.findByEmail(user.getEmail()) != null)
            throw new RegistrationException("Such email is already used","RegistrationService.java: RegistrationException");

        String activationCode = randomUtil.generateCode();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(false);
        user.setActivationCode(activationCode);
        user.setAvatarUrl("http://localhost:8080/images/default_avatar.jpg");
        usersService.addUser(user);

        emailService.sendActivationCode(user, activationCode);
    }

    public void registerAdmin(User user) throws RegistrationException{
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setAvatarUrl("http://localhost:8080/images/default_avatar.jpg");
        usersService.addAdmin(user);
    }
}
