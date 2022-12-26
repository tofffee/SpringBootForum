//package com.example.springforumapp.chat.controllers.web;
//
//import com.example.springforumapp.chat.models.domain.ChatMessage;
//import com.example.springforumapp.chat.services.ChatService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/chat")
//public class ChatController {
//
//    private final ChatService chatService;
//
//    @Autowired
//    public ChatController(ChatService chatService)
//    {
//        this.chatService = chatService;
//    }
//
//
//    @GetMapping()
//    public String getChatPage(Model model)
//    {
//        List<ChatMessage> chatMessages = chatService.getAllMessages();
//        model.addAttribute("messages", chatMessages);
//        return "chat/chatPage";
//    }
//
//
//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
//    public ChatMessage sendMessage (@Payload ChatMessage chatMessage)
//    {
//        System.out.println("ChatController : person send message to chat (" + chatMessage.getTextOfChatMessage() + ")");
//        return chatMessage;
//    }
//
//}
