package com.example.springforumapp.auth.services;



import com.example.springforumapp.auth.models.dto.ForgetPasswordRequestDTO;
import com.example.springforumapp.auth.models.dto.LoginRequestDTO;
import com.example.springforumapp.auth.util.exceptions.AuthException;
import com.example.springforumapp.common.util.RandomUtil;
import com.example.springforumapp.email.EmailService;
import com.example.springforumapp.security.JWTUtil;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.services.UsersService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthService implements IAuthSevice {
    private final UsersService usersService;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final RandomUtil randomUtil;
    public AuthService(UsersService usersService, EmailService emailService, AuthenticationManager authenticationManager, JWTUtil jwtUtil, RandomUtil randomUtil) {
        this.usersService = usersService;
        this.emailService = emailService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.randomUtil = randomUtil;
    }

    public String authenticate(LoginRequestDTO loginRequestDTO) throws AuthException {
        User user = usersService.findByUsernameOrEmail(loginRequestDTO.getUsernameOrEmail());
        if (user==null){
            throw new AuthException("User with such username or email is not registered","AuthService.java : AuthException");
        }

        UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(user.getUsername(), loginRequestDTO.getPassword());
        try{
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException badCredentialsException){
            throw new AuthException("Incorrect credentials","AuthService.java: AuthException");
        }
        return jwtUtil.generateToken(user.getUsername());
    }

    public void forgetPassword(ForgetPasswordRequestDTO forgetPasswordRequestDTO) {
        if (usersService.findByUsername(forgetPasswordRequestDTO.getUserNameOrEmail()) == null)
            throw new AuthException("User with such username is not registered","AuthService.java: AuthException");

        if (usersService.findByEmail(forgetPasswordRequestDTO.getUserNameOrEmail()) == null)
            throw new AuthException("User with such email is not registered","AuthService.java: AuthException");

        User user = usersService.findByUsernameOrEmail(forgetPasswordRequestDTO.getUserNameOrEmail());
        String resetPasswordCode =  randomUtil.generateCode();
        emailService.sendResetPasswordCode(user,resetPasswordCode);
    }
}
