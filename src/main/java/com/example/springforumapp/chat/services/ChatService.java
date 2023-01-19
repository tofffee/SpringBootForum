package com.example.springforumapp.chat.services;

import com.example.springforumapp.chat.models.domain.ChatMessage;
import com.example.springforumapp.chat.repositories.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatService implements IChatService
{
    private final ChatRepository chatRepository;

    public List<ChatMessage> getAllMessages()
    {
        List<ChatMessage> messagesList = chatRepository.findAll();
        return messagesList;
    }

    @Transactional
    public void saveMessage(ChatMessage chatMessage)
    {
        chatMessage.setTimeOfChatMessage(LocalDate.now());
        chatRepository.save(chatMessage);
    }

}
