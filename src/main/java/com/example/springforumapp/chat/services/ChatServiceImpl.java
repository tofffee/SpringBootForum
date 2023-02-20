//package com.example.springforumapp.chat.services;
//
//import com.example.springforumapp.boards.models.domain.Board;
//import com.example.springforumapp.boards.models.dto.BoardOutDTO;
//import com.example.springforumapp.chat.models.domain.ChatMessage;
//import com.example.springforumapp.chat.models.domain.Dialog;
//import com.example.springforumapp.chat.models.dto.ChatMessageInDTO;
//import com.example.springforumapp.chat.models.dto.ChatMessageOutDTO;
//import com.example.springforumapp.chat.repositories.ChatRepository;
//import com.example.springforumapp.users.models.domain.User;
//import com.example.springforumapp.users.models.dto.UserDTO;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@Transactional(readOnly = true)
//@Slf4j
//@RequiredArgsConstructor
//public class ChatServiceImpl implements ChatService
//{
//    private final ChatRepository chatRepository;
//    private final ModelMapper modelMapper;
//
//    @Override
//    public List<ChatMessageOutDTO> findAllMessagesByDialog(Dialog dialog) {
//        List<ChatMessage> chatMessages = chatRepository.findChatMessageByDialogId(dialog.getId());
//        return chatMessagesToOutDTOs(chatMessages);
//    }
//
//
//    @Transactional
//    @Override
//    public ChatMessageOutDTO createMessage(ChatMessageInDTO chatMessageInDTO, Dialog dialog, User user) {
//        ChatMessage chatMessage = modelMapper.map(chatMessageInDTO, ChatMessage.class);
//        chatMessage.setCreatedAt(LocalDate.now());
//        chatMessage.setDialog(dialog);
//        chatMessage.setUser(user);
//        chatRepository.save(chatMessage);
//        return chatMessageToOutDTO(chatMessage);
//    }
//
//    private ChatMessageOutDTO chatMessageToOutDTO(ChatMessage chatMessage){
//        ChatMessageOutDTO chatMessageOutDTO = modelMapper.map(chatMessage, ChatMessageOutDTO.class);
//        UserDTO userDTO = modelMapper.map(chatMessage.getUser(), UserDTO.class);
//        chatMessageOutDTO.setUserDTO(userDTO);
//        return chatMessageOutDTO;
//    }
//
//    private List<ChatMessageOutDTO> chatMessagesToOutDTOs(List<ChatMessage> chatMessages){
//        List<ChatMessageOutDTO> chatMessageOutDTOs = new ArrayList<>();
//        chatMessages.forEach(chatMessage -> {
//            chatMessageOutDTOs.add(chatMessageToOutDTO(chatMessage));
//        });
//        return chatMessageOutDTOs;
//    }
//
//
//}
