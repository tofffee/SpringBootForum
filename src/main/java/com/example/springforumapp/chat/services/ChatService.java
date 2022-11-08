package com.example.springforumapp.chat.services;

import com.example.springforumapp.chat.models.domain.ChatMessage;
import com.example.springforumapp.chat.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ChatService
{
    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository)
    {
        this.chatRepository = chatRepository;
    }

    public List<ChatMessage> getAllMessages()
    {
        List<ChatMessage> messagesList = chatRepository.findAll();
        return messagesList;
    }
    public void saveMessage(ChatMessage chatMessage)
    {
        chatMessage.setTimeOfChatMessage(LocalDate.now());
        chatRepository.save(chatMessage);
    }

}