package com.example.springforumapp.chat.controllers.api;


import com.example.springforumapp.chat.models.dto.DialogOutDTO;
import com.example.springforumapp.chat.services.DialogServiceImpl;
import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.services.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class ChatControllerApi {
  //  private final ChatServiceImpl chatService;
    private final DialogServiceImpl dialogService;
    private final UsersServiceImpl usersService;

    @GetMapping("/dialog/test/{userId}")
    public ResponseEntity<ResponseApi> getDialog(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                 @PathVariable("userId") long userId) {

        User user1 = usersService.findById(userDetails.getUser().getId());
        User user2 = usersService.findById(userId);
        DialogOutDTO dto = dialogService.findByUsers(user1, user2);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, dto));
    }

//    @GetMapping("/dialog/{id}")
//    public ResponseEntity<ResponseApi> getMessageInDialog(@PathVariable("id") long id) {
//        Dialog dialog = dialogService.findById(id);
//        List<ChatMessageOutDTO> dtos = chatService.findAllMessagesByDialog(dialog);
//        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, dtos));
//    }
//
//    @PostMapping("/dialog/{id}")
//    public ResponseEntity<ResponseApi> createMessage(@PathVariable("id") long id,
//                                                     @AuthenticationPrincipal UserDetailsImpl userDetails,
//                                                     @RequestBody @Valid ChatMessageInDTO chatMessageInDTO) {
//        Dialog dialog = dialogService.findById(id);
//        User user = usersService.findById(userDetails.getUser().getId());
//        ChatMessageOutDTO dto = chatService.createMessage(chatMessageInDTO, dialog, user);
//        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, dto));
//    }
}
