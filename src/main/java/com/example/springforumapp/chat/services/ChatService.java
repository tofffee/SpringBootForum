package com.example.springforumapp.chat.services;

import com.example.springforumapp.chat.models.domain.ChatMessage;
import com.example.springforumapp.chat.models.domain.Dialog;
import com.example.springforumapp.chat.models.dto.ChatMessageInDTO;
import com.example.springforumapp.chat.models.dto.ChatMessageOutDTO;
import com.example.springforumapp.users.models.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChatService {
    public List<ChatMessageOutDTO> findAllMessagesByDialog(Dialog dialog);

    public ChatMessageOutDTO createMessage(ChatMessageInDTO chatMessageInDTO, Dialog dialog, User user);
}
