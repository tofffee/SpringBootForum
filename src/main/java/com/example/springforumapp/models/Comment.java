package com.example.springforumapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

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


    @ManyToOne
    private Comment replied_to_comment;

    @OneToMany(mappedBy = "replied_to_comment")
    private List<Comment> comments_replied_to_this_comment;

    public Comment() {}

    public Comment(int id, String textOfComment, LocalDate timeOfComment, Publication publication, Comment replied_to_comment, List<Comment> comments_replied_to_this_comment) {
        this.id = id;
        this.textOfComment = textOfComment;
        this.timeOfComment = timeOfComment;
        this.publication = publication;
        this.replied_to_comment = replied_to_comment;
        this.comments_replied_to_this_comment = comments_replied_to_this_comment;
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

    public Comment getReplied_to_comment() {
        return replied_to_comment;
    }

    public void setReplied_to_comment(Comment replied_to_comment) {
        this.replied_to_comment = replied_to_comment;
    }

    public List<Comment> getComments_replied_to_this_comment() {
        return comments_replied_to_this_comment;
    }

    public void setComments_replied_to_this_comment(List<Comment> comments_replied_to_this_comment) {
        this.comments_replied_to_this_comment = comments_replied_to_this_comment;
    }
}