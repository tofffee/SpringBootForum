package com.example.springforumapp.auth.services;

import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.repositories.UsersRepository;
import com.example.springforumapp.users.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class RegistrationService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Autowired
    public RegistrationService(UsersRepository usersRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    //Проверка существует ли уже пользователем с таким именем в БД
    public boolean checkUsernameIsAlreadyTaken(String username){
        Optional<User> user = usersRepository.findUserByUsername(username);
        return user.isPresent();
    }

    public void registerUser(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("ROLE_USER");
        user.setEnabled(false);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setAvatarUrl("http://localhost:8080/images/default_avatar.jpg");

        usersRepository.save(user);
        //отправка сообщения на почту
        String message = "Hello," + user.getUsername() + "activate your account : " + "http://localhost:8080/registration/activate/"+user.getActivationCode() ;
        emailService.send(user.getEmail(),"Activate Your account",message);
    }
}
