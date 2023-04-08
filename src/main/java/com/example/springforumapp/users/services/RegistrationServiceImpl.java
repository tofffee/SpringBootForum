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
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final RandomUtil randomUtil;

    @Override
    @Transactional
    public User registerUser(RegisterInDTO registerInDTO) throws RegistrationException{
        if (usersRepository.existsByUsername(registerInDTO.getUsername()))
            throw new RegistrationException("User with such username is already registered","RegistrationService.java: RegistrationException");

        if (usersRepository.existsByEmail(registerInDTO.getEmail()))
            throw new RegistrationException("Such email is already used","RegistrationService.java: RegistrationException");

        User user = new User();
        user.setUsername(registerInDTO.getUsername());
        user.setEmail(registerInDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerInDTO.getPassword()));
        user.setEnabled(false);
        user.setActivationCode(randomUtil.generateCode());
        user.setAvatarUrl("http://localhost:80/images/default_avatar.jpg");

        Set<Role> roles = new HashSet<>();
        Role userRole = rolesRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        usersRepository.save(user);
        return user;
    }
}
