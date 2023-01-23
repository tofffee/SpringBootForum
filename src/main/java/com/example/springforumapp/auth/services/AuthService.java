package com.example.springforumapp.auth.services;



import com.example.springforumapp.auth.models.dto.ForgetPasswordRequestDTO;
import com.example.springforumapp.auth.models.dto.LoginRequestDTO;
import com.example.springforumapp.auth.models.dto.LoginResponseDTO;
import com.example.springforumapp.auth.util.exceptions.AuthException;
import com.example.springforumapp.common.util.RandomUtil;
import com.example.springforumapp.email.services.EmailService;
import com.example.springforumapp.security.JWTUtil;
import com.example.springforumapp.security.UserDetailsServiceImpl;
import com.example.springforumapp.users.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService implements IAuthSevice {
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public void authenticate(LoginRequestDTO loginRequestDTO) throws AuthException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        } catch (BadCredentialsException badCredentialsException){
            throw new AuthException("Incorrect credentials","AuthService.java: AuthException");
        }
    }

    public void forgetPassword(ForgetPasswordRequestDTO forgetPasswordRequestDTO) {
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
}
