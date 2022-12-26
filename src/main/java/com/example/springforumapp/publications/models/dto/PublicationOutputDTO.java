package com.example.springforumapp.publications.models.dto;

import com.example.springforumapp.users.models.dto.UserDTO;

import java.time.LocalDate;


public class PublicationOutputDTO {

    private int id;

    private UserDTO userDTO;
    private String nameOfPublication;


    private String textOfPublication;


    private LocalDate timeOfPublication;


    public PublicationOutputDTO() {}


    public PublicationOutputDTO(int id, UserDTO userDTO, String nameOfPublication, String textOfPublication, LocalDate timeOfPublication) {
        this.id = id;
        this.userDTO = userDTO;
        this.nameOfPublication = nameOfPublication;
        this.textOfPublication = textOfPublication;
        this.timeOfPublication = timeOfPublication;
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

    public String getNameOfPublication() {
        return nameOfPublication;
    }

    public void setNameOfPublication(String nameOfPublication) {
        this.nameOfPublication = nameOfPublication;
    }

    public String getTextOfPublication() {
        return textOfPublication;
    }

    public void setTextOfPublication(String textOfPublication) {
        this.textOfPublication = textOfPublication;
    }

    public LocalDate getTimeOfPublication() {
        return timeOfPublication;
    }

    public void setTimeOfPublication(LocalDate timeOfPublication) {
        this.timeOfPublication = timeOfPublication;
    }
}
