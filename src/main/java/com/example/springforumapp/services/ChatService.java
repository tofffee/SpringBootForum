package com.example.springforumapp.services;

import com.example.springforumapp.models.chat.Message;
import com.example.springforumapp.repositories.ChatRepository;
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

    public List<Message> getAllMessages()
    {
        List<Message> messagesList = chatRepository.findAll();
        return messagesList;
    }
    public void saveMessage(Message message)
    {
        message.setTimeOfMessage(LocalDate.now());
        chatRepository.save(message);
    }

}
