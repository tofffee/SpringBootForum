package com.example.springforumapp.users.services;

import com.example.springforumapp.users.models.domain.Role;
import com.example.springforumapp.users.models.dto.*;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.repositories.RolesRepository;
import com.example.springforumapp.users.repositories.UsersRepository;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.util.exceptions.ActivationAccountException;
import com.example.springforumapp.users.util.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    private final ModelMapper modelMapper;
    @Override
    public User findById(long id) {
        Optional<User> user =  usersRepository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("User was not found", "UsersService.java: UserNotFoundException");
        return user.get();
    }
    @Override
    public User findByUsername(String username) {
        Optional<User> user =  usersRepository.findByUsername(username);
        if(user.isEmpty())
            throw new UserNotFoundException("User was not found", "UsersService.java: UserNotFoundException");
        return user.get();
    }
    @Override
    public User findByEmail(String email){
        Optional<User> user =  usersRepository.findByEmail(email);
        if(user.isEmpty())
            throw new UserNotFoundException("User was not found", "UsersService.java: UserNotFoundException");
        return user.get();
    }
    @Override
    public User findByUsernameOrEmail(String usernameOrEmail) {
        Optional<User> user = usersRepository.findByUsername(usernameOrEmail);
        if(user.isEmpty())
            throw new UserNotFoundException("User was not found", "UsersService.java: UserNotFoundException");
        return user.get();
    }
    @Override
    public boolean existsByUsername(String username) {
        Optional<User> user = usersRepository.findByUsername(username);
        return user.isPresent();
    }
    @Override
    public boolean existsByEmail(String email) {
        Optional<User> user = usersRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public AuthUserInfoOutDTO getAuthUserInfo(UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return modelMapper.map(user, AuthUserInfoOutDTO.class);
    }

    @Override
    public UserDTO getProfile(long id) {
        User user = findById(id);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void forgetPassword(ForgetPasswordInDTO forgetPasswordInDTO) {
//        if (usersService.findByUsername(forgetPasswordRequestDTO.getUserNameOrEmail()) == null)
//            throw new AuthException("User with such username is not registered","AuthService.java: AuthException");
//
//        if (usersService.findByEmail(forgetPasswordRequestDTO.getUserNameOrEmail()) == null)
//            throw new AuthException("User with such email is not registered","AuthService.java: AuthException");
//
//        User user = usersService.findByUsernameOrEmail(forgetPasswordRequestDTO.getUserNameOrEmail());
//        String resetPasswordCode =  randomUtil.generateCode();
//        emailService.sendResetPasswordCode(user,resetPasswordCode);
    }


    @Override
    @Transactional
    public void activateUser(UserDetailsImpl userDetailsImpl, ActivationCodeRequestDTO activationCodeRequestDTO)
    {
        User user = usersRepository.findByUsername(userDetailsImpl.getUsername()).get();
        if (user.getEnabled()){
            throw new ActivationAccountException("Your account is already activated","UsersService.java: ActivationProfileException");
        }

        if (user.getActivationCode().equals(activationCodeRequestDTO.getCode())){
            user.setEnabled(true);
            user.setActivationCode(null);
        } else throw new ActivationAccountException("Your activation code is not correct","UsersService.java: ActivationProfileException");
    }


    @Override
    @Transactional
    public void deleteUser(long id) {
        Optional<User> user = usersRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("Such user is not found","UsersService.java : UserNotFoundException");

        usersRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void grantAdminRole(long id){
        Optional<User> user = usersRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("Such user is not found","UsersService.java : UserNotFoundException");

        Set<Role> roles = new HashSet<>();
        roles.add(rolesRepository.findByName("ROLE_USER"));
        roles.add(rolesRepository.findByName("ROLE_ADMIN"));
        user.get().setRoles(roles);
        usersRepository.save(user.get());
    }

    @Override
    @Transactional
    public void ungrantAdminRole(long id){
        Optional<User> user = usersRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("Such user is not found","UsersService.java : UserNotFoundException");

        Set<Role> roles = new HashSet<>();
        roles.add(rolesRepository.findByName("ROLE_USER"));
        user.get().setRoles(roles);
        usersRepository.save(user.get());
    }

}