package com.example.springforumapp.services;

import com.example.springforumapp.models.User;
import com.example.springforumapp.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RegistrationService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Проверка существует ли уже пользователем с таким именем в БД
    public Boolean checkIfUserAlreadyExists(String username){
        Optional<User> user = usersRepository.findUserByUsername(username);
        return user.isPresent();
    }

    public void registerUser(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("ROLE_USER");
        usersRepository.save(user);
    }
}
