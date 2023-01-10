package com.example.springforumapp.users.services;

import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UsersServiceTest {
    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    void addUser() {
        User user = new User();
        user.setUsername("vasili");
        user.setAvatarUrl("http://localhost:80/static/avatar.jpg");
        user.setEmail("vasili@mail.ru");
        user.setPassword("12345");
        user.setActivationCode("1425");
        user.setEnabled(false);

        usersService.addUser(user);
        Mockito.verify(usersRepository, Mockito.times(1)).save(user);
    }
}