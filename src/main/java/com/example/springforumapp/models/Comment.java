package com.example.springforumapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "Comments")

public class Comment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "text")
    @NotNull
    @Size(min = 1,message = "Comment error (text of publication is too small)")
    private String textOfComment;

    @Column(name = "time")
    private LocalDate timeOfComment;

    @ManyToOne
    @JoinColumn(name = "publication_id", referencedColumnName = "id")
    private Publication publication;

    public Comment() { }

    public Comment(int id, String textOfComment, LocalDate timeOfComment, Publication publication)
    {
        this.id = id;
        this.textOfComment = textOfComment;
        this.timeOfComment = timeOfComment;
        this.publication = publication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextOfComment() {
        return textOfComment;
    }

    public void setTextOfComment(String textOfComment) {
        this.textOfComment = textOfComment;
    }

    public LocalDate getTimeOfComment() {
        return timeOfComment;
    }

    public void setTimeOfComment(LocalDate timeOfComment) {
        this.timeOfComment = timeOfComment;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }
}
