package com.example.springforumapp.chat.services;

import com.example.springforumapp.chat.models.domain.ChatMessage;
import com.example.springforumapp.chat.repositories.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService
{
    private final ChatRepository chatRepository;

    public List<ChatMessage> getAllMessages() {
        List<ChatMessage> messagesList = chatRepository.findAll();
        return messagesList;
    }

    @Transactional
    public void saveMessage(ChatMessage chatMessage) {
        chatMessage.setCreatedAt(LocalDate.now());
        chatRepository.save(chatMessage);
    }

}
