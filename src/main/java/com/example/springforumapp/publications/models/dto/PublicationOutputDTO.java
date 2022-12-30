package com.example.springforumapp.publications.models.dto;

import com.example.springforumapp.users.models.dto.UserDTO;

import java.time.LocalDate;


public class PublicationOutputDTO {

    private int id;

    private UserDTO userDTO;
    private String name;


    private String text;


    private LocalDate dateOfCreation;


    public PublicationOutputDTO() {}

    public PublicationOutputDTO(int id, UserDTO userDTO, String name, String text, LocalDate dateOfCreation) {
        this.id = id;
        this.userDTO = userDTO;
        this.name = name;
        this.text = text;
        this.dateOfCreation = dateOfCreation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
