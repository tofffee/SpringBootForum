package com.example.springforumapp.controllers;

import com.example.springforumapp.dto.chat.MessageDTO;
import com.example.springforumapp.models.chat.Message;
import com.example.springforumapp.services.ChatService;
import com.example.springforumapp.util.chat.MessageNotSavedException;
import com.example.springforumapp.util.chat.MessageResponseError;
import com.example.springforumapp.util.comments.CommentNotSavedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService)
    {
        this.chatService = chatService;
    }


    @GetMapping()
    public String getChatPage(Model model)
    {
        List<Message> messages = chatService.getAllMessages();
        model.addAttribute("messages", messages);
        return "chat/chatPage";
    }

    @ResponseBody
    @PostMapping()
    public ResponseEntity<HttpStatus> sendMessageChat(@RequestBody @Valid MessageDTO messageDTO, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errorMessage
                        .append(error.getField())
                        .append("-")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }

            throw new MessageNotSavedException(errorMessage.toString());
        }

        Message message = new Message();
        message.setTextOfMessage(messageDTO.getTextOfMessage());
        chatService.saveMessage(message);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MessageResponseError> handleException(MessageNotSavedException messageNotSavedException)
    {
        MessageResponseError messageResponseError = new MessageResponseError(
                messageNotSavedException.getMessage(),
                System.currentTimeMillis()
        );
                return new ResponseEntity<>(messageResponseError, HttpStatus.BAD_REQUEST);
    }
}
