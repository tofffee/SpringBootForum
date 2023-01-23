package com.example.springforumapp.registration.services;

import com.example.springforumapp.email.services.EmailService;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.repositories.UsersRepository;
import com.example.springforumapp.users.services.UsersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RegistrationServiceTest {

    @Autowired
    private RegistrationService registrationService;

    @MockBean
    private UsersService usersService;
    @MockBean
    private UsersRepository usersRepository;

    @MockBean
    private EmailService emailService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void registerUser() {
        User user = new User();
        user.setUsername("vasili");
        user.setEmail("vasili@mail.ru");
        user.setPassword("12345");
      //  registrationService.registerUser(user);

        Mockito.verify(usersRepository, Mockito.times(1)).save(user);
        Mockito.verify(usersService, Mockito.times(1)).addUser(user);
        //Mockito.verify(emailService, Mockito.times(1)).addUser(user);
    }
}