package com.example.springforumapp.publications.models.dto;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.comments.models.domain.Comment;

import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;


public class PublicationDTO {

    private int id;

    private String nameOfPublication;


    private String textOfPublication;


    private LocalDate timeOfPublication;


    private Board board;

    private List<Comment> comments;

    public PublicationDTO() {}

    public PublicationDTO(int id, String nameOfPublication, String textOfPublication, LocalDate timeOfPublication, Board board, List<Comment> comments) {
        this.id = id;
        this.nameOfPublication = nameOfPublication;
        this.textOfPublication = textOfPublication;
        this.timeOfPublication = timeOfPublication;
        this.board = board;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
