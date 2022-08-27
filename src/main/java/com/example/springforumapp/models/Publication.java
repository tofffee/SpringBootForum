package com.example.springforumapp.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Publications")
public class Publication {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull
    @Size(min = 1,message = "Publication error (name of publication is too small)")
    private String nameOfPublication;


    @Column(name = "text")
    @NotNull
    @Size(min = 1,message = "Publication error (text of publication is too small)")
    private String textOfPublication;

    @Column(name = "time")
    private LocalDate timeOfPublication;

    @ManyToOne
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    private Board board;

    public Publication(){};

    public Publication(int id, String nameOfPublication, String textOfPublication, LocalDate timeOfPublication, Board board) {
        this.id = id;
        this.nameOfPublication = nameOfPublication;
        this.textOfPublication = textOfPublication;
        this.timeOfPublication = timeOfPublication;
        this.board = board;
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
}
