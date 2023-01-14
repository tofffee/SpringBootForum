package com.example.springforumapp.email.services;

import com.example.springforumapp.email.models.domain.Email;
import com.example.springforumapp.email.repositories.EmailRepository;
import com.example.springforumapp.email.util.exceptions.EmailException;
import com.example.springforumapp.users.models.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class EmailService implements IEmailService {
    private final EmailRepository emailRepository;
    private final JavaMailSender javaMailSender;

    @Value("${forum.email_address}")
    private String from;


    @Autowired
    public EmailService(EmailRepository emailRepository, JavaMailSender javaMailSender) {
        this.emailRepository = emailRepository;
        this.javaMailSender = javaMailSender;
    }

    @Transactional
    public void sendActivationCode(User user, String activationCode) throws EmailException {
        String subject = "Account activation";
        String text = "Hello, " + user.getUsername() + ", your activation code is : " + activationCode;
        sendMessage(from, subject, text, user);
    }

    @Transactional
    public void sendResetPasswordCode(User user, String resetPasswordCode) {
        String subject = "Reset password";
        String text = "Hello, " + user.getUsername() + ", your reset password code is : " + resetPasswordCode;
        sendMessage(from, subject, text, user);
    }

    private void sendMessage(String from, String subject, String text, User user) throws EmailException {
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
