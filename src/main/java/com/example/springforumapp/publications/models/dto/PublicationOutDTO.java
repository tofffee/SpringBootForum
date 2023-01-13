package com.example.springforumapp.publications.models.dto;


import com.example.springforumapp.files.models.dto.UpFileOutDTO;
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
public class PublicationOutDTO {
    private int id;
    private UserDTO userDTO;
    private String name;
    private String text;
    private LocalDate dateOfCreation;
    private List<UpFileOutDTO> upfiles;
}
