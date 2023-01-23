package com.example.springforumapp.users.services;



import com.example.springforumapp.users.models.dto.ForgetPasswordInDTO;
import com.example.springforumapp.users.models.dto.LoginInDTO;
import com.example.springforumapp.users.util.exceptions.AuthException;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.security.UserDetailsServiceImpl;
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
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;

    public UserDetailsImpl login(LoginInDTO loginInDTO) throws AuthException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginInDTO.getUsername(), loginInDTO.getPassword()));
        } catch (BadCredentialsException badCredentialsException){
            throw new AuthException("Incorrect credentials","AuthService.java: AuthException");
        }
        return (UserDetailsImpl)userDetailsService.loadUserByUsername(loginInDTO.getUsername());
    }

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
}
