package com.example.springforumapp.registration.services;

import com.example.springforumapp.users.models.domain.User;

public interface IRegistrationService {
    public void registerUser(User user);
    public void registerAdmin(User user);
}
