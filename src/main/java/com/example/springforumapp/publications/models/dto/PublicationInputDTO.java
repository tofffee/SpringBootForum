package com.example.springforumapp.publications.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class PublicationInputDTO {

    @NotNull
    @Size(min = 3,message = "Publication error (name of publication is too small)")
    private String name;

    @NotNull
    @Size(min = 3,message = "Publication error (text of publication is too small)")
    private String text;

    public PublicationInputDTO() {}

    public PublicationInputDTO(String name, String text) {
        this.name = name;
        this.text = text;
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
}
