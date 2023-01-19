package com.example.springforumapp.chat.services;

import com.example.springforumapp.chat.models.domain.ChatMessage;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface IChatService {
    public List<ChatMessage> getAllMessages();

    public void saveMessage(ChatMessage chatMessage);
}
