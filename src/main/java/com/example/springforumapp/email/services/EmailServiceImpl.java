package com.example.springforumapp.email.services;

import com.example.springforumapp.email.models.domain.Email;
import com.example.springforumapp.email.repositories.EmailRepository;
import com.example.springforumapp.email.util.exceptions.EmailException;
import com.example.springforumapp.users.models.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final EmailRepository emailRepository;
    private final JavaMailSender javaMailSender;
    @Value("${public.email_address}")
    private String from;

    @Transactional
    public void sendActivationCode(User user) throws EmailException {
        String subject = "Account activation";
        String text = "Hello, " + user.getUsername() + ", your activation code is : " + user.getActivationCode();
        sendMessage(subject, text, user);
    }

    @Transactional
    public void sendResetPasswordCode(User user, String resetPasswordCode) {
        String subject = "Reset password";
        String text = "Hello, " + user.getUsername() + ", your reset password code is : " + resetPasswordCode;
        sendMessage(subject, text, user);
    }

    private void sendMessage(String subject, String text, User user) throws EmailException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        simpleMailMessage.setTo(user.getEmail());

        Email email = new Email();
        email.setSubject(subject);
        email.setText(text);
        email.setSentTo(user);

        try{
            javaMailSender.send(simpleMailMessage);
            emailRepository.save(email);
        } catch (Exception e){
            throw new EmailException("Email can't be sent","EmailService.java: EmailException");
        }
    }
}
