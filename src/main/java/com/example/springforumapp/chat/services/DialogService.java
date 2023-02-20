package com.example.springforumapp.chat.services;

import com.example.springforumapp.chat.models.domain.Dialog;
import com.example.springforumapp.chat.models.dto.DialogOutDTO;
import com.example.springforumapp.chat.util.exceptions.DialogNotFoundException;
import com.example.springforumapp.users.models.domain.User;

public interface DialogService {

    public Dialog findById(long id);
    public DialogOutDTO findByUsers(User user1, User user2);
}
