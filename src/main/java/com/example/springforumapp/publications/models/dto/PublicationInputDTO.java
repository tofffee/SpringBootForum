package com.example.springforumapp.publications.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class PublicationInputDTO {
    @NotNull
    @Size(min = 1,message = "Publication error (name of publication is too small)")
    private String nameOfPublication;

    @NotNull
    @Size(min = 1,message = "Publication error (text of publication is too small)")
    private String textOfPublication;



    public PublicationInputDTO() {}

    public PublicationInputDTO(String nameOfPublication, String textOfPublication) {
        this.nameOfPublication = nameOfPublication;
        this.textOfPublication = textOfPublication;
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


}
