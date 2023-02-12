package com.example.springforumapp.chat.controllers.api;


import com.example.springforumapp.chat.services.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatControllerApi {
    private final ChatServiceImpl chatService;

}
