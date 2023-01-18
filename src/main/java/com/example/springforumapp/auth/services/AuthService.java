package com.example.springforumapp.auth.services;



import com.example.springforumapp.auth.models.dto.ForgetPasswordRequestDTO;
import com.example.springforumapp.auth.models.dto.LoginRequestDTO;
import com.example.springforumapp.auth.util.exceptions.AuthException;
import com.example.springforumapp.common.util.RandomUtil;
import com.example.springforumapp.email.services.EmailService;
import com.example.springforumapp.security.JWTUtil;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService implements IAuthSevice {
    private final UsersService usersService;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final RandomUtil randomUtil;

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
