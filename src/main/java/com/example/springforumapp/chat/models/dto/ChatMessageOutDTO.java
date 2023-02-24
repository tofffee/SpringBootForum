package com.example.springforumapp.chat.models.dto;

import com.example.springforumapp.users.models.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageOutDTO {
    private long id;
    private String text;
    private LocalDate createdAt;
    @JsonProperty("user")
    private UserDTO userDTO;
}
