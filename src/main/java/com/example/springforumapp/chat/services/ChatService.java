package com.example.springforumapp.chat.services;

import com.example.springforumapp.chat.models.domain.ChatMessage;

import java.util.List;

public interface ChatService {
    public List<ChatMessage> getAllMessages();

    public void saveMessage(ChatMessage chatMessage);
}
