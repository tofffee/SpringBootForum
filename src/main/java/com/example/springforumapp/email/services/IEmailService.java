package com.example.springforumapp.email.services;

import com.example.springforumapp.users.models.domain.User;

public interface IEmailService {
    public void sendActivationCode(User user);
    public void sendResetPasswordCode(User user, String resetPasswordCode);
}
