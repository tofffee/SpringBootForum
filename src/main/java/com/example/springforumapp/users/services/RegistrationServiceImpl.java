package com.example.springforumapp.users.services;

import com.example.springforumapp.common.util.RandomUtil;
import com.example.springforumapp.users.models.domain.Role;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.models.dto.RegisterInDTO;
import com.example.springforumapp.users.repositories.RolesRepository;
import com.example.springforumapp.users.repositories.UsersRepository;
import com.example.springforumapp.users.util.exceptions.RegistrationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RandomUtil randomUtil;

    @Override
    @Transactional
    public User registerUser(RegisterInDTO registerInDTO) throws RegistrationException{
        if (usersRepository.existsByUsername(registerInDTO.getUsername()))
            throw new RegistrationException("User with such username is already registered","RegistrationService.java: RegistrationException");

        if (usersRepository.existsByEmail(registerInDTO.getEmail()))
            throw new RegistrationException("Such email is already used","RegistrationService.java: RegistrationException");

        User user = modelMapper.map(registerInDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(false);
        user.setActivationCode(randomUtil.generateCode());
        user.setAvatarUrl("http://localhost:80/images/default_avatar.jpg");

        Role role = rolesRepository.findByName("ROLE_USER");
        user.setRoles(Set.of(role));
        usersRepository.save(user);
        return user;
    }
}
