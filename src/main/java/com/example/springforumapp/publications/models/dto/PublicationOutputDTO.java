package com.example.springforumapp.publications.models.dto;

import java.time.LocalDate;


public class PublicationOutputDTO {

    private int id;

    private int userId;
    private String nameOfPublication;


    private String textOfPublication;


    private LocalDate timeOfPublication;


    public PublicationOutputDTO() {}


    public PublicationOutputDTO(int id, int userId, String nameOfPublication, String textOfPublication, LocalDate timeOfPublication) {
        this.id = id;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
