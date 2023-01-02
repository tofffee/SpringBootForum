package com.example.springforumapp.publications.models.dto;

import com.example.springforumapp.users.models.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationOutputDTO {
    private int id;
    private UserDTO userDTO;
    private String name;
    private String text;
    private LocalDate dateOfCreation;
}
