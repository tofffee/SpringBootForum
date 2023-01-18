package com.example.springforumapp.comments.models.dto;


import com.example.springforumapp.users.models.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentOutputDTO {
    private int id;
    private String text;
    private LocalDate dateOfCreation;
    private UserDTO userDTO;
}
