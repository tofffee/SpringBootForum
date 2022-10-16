package com.example.springforumapp.dto;

import com.example.springforumapp.models.Publication;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CommentDTO {
    @NotNull
    @Size(min = 1,message = "Comment error (text of publication is too small)")
    private String textOfComment;

    public  CommentDTO( ) { }

    public CommentDTO(String textOfComment)
    {
        this.textOfComment = textOfComment;
    }

    public String getTextOfComment() {
        return textOfComment;
    }

    public void setTextOfComment(String textOfComment) {
        this.textOfComment = textOfComment;
    }

}

