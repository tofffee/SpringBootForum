package com.example.springforumapp.comments.models.domain;

import com.example.springforumapp.publications.models.domain.Publication;

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
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> childComments;

    public Comment() {}

    public Comment(int id, String textOfComment, LocalDate timeOfComment, Publication publication, Comment parentComment, List<Comment> childComments) {
        this.id = id;
        this.textOfComment = textOfComment;
        this.timeOfComment = timeOfComment;
        this.publication = publication;
        this.parentComment = parentComment;
        this.childComments = childComments;
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

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public List<Comment> getChildComments() {
        return childComments;
    }

    public void setChildComments(List<Comment> childComments) {
        this.childComments = childComments;
    }
}