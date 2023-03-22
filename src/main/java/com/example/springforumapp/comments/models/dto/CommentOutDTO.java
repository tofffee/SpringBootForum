package com.example.springforumapp.comments.models.dto;


import com.example.springforumapp.users.models.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentOutDTO {
    private long id;
    private String text;
    private LocalDate createdAt;
    private Long parentCommentId;
    private List<CommentOutDTO> childComments;
    @JsonProperty("user")
    private UserDTO userDTO;
}
