package com.example.springforumapp.email;

import com.example.springforumapp.registration.services.RegistrationService;
import com.example.springforumapp.users.models.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class EmailServiceTest {

    @MockBean
    private EmailService emailService;

//    @MockBean
//    private JavaMailSender javaMailSender;

    @Test
    void sendActivationCode() {
        User user = new User();
        user.setUsername("vasili");
        user.setEmail("vasili@mail.ru");
        user.setPassword("12345");
        String activationCode = "1435";
        emailService.sendActivationCode(user, activationCode);
        Mockito.verify(emailService, Mockito.times(1)).sendActivationCode(user, activationCode);
    }

    @Test
    void sendResetPasswordCode() {
    }
}