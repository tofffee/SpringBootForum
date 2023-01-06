package com.example.springforumapp.email;

import com.example.springforumapp.users.models.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {
    private final JavaMailSender javaMailSender;
    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendActivationCode(User user, String activationCode)
    {
        String message = "Hello, " + user.getUsername() + ", your activation code is : " + activationCode;
        String subject = "Account activation";
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("springforumapp@yandex.ru");
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        try{
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e){
            System.out.println("EmailService.java ERROR : " + e.toString());
        }
    }

    @Override
    public void sendResetPasswordCode(User user, String resetPasswordCode) {
        String message = "Hello, " + user.getUsername() + ", your reset password code is : " + resetPasswordCode;
        String subject = "Reset password";
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("springforumapp@yandex.ru");
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        try{
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e){
            System.out.println("EmailService.java ERROR : " + e.toString());
        }
    }
}
