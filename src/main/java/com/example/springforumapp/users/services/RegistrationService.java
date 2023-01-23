package com.example.springforumapp.users.services;

import com.example.springforumapp.common.util.RandomUtil;
import com.example.springforumapp.users.models.dto.RegisterInDTO;
import com.example.springforumapp.users.util.exceptions.RegistrationException;
import com.example.springforumapp.users.models.domain.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegistrationService implements IRegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RandomUtil randomUtil;

    @Transactional
    public User registerUser(RegisterInDTO registerInDTO) throws RegistrationException{
//        if (usersService.isUsernameTaken(registerRequestDTO.getUsername()))
//            throw new RegistrationException("Such username is already taken","RegistrationService.java: RegistrationException");
//        if (usersService.isEmailTaken(registerRequestDTO.getEmail()))
//            throw new RegistrationException("Such email is already taken","RegistrationService.java: RegistrationException");

        User user = modelMapper.map(registerInDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(false);
        user.setActivationCode(randomUtil.generateCode());
        user.setAvatarUrl("http://localhost:80/images/default_avatar.jpg");
        return user;
    }
}
