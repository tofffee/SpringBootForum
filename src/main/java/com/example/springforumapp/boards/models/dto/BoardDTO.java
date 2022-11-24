package com.example.springforumapp.boards.models.dto;

import com.example.springforumapp.publications.models.domain.Publication;

import javax.persistence.*;
import java.util.List;


public class BoardDTO {

    private int id;

    private String name;
    public BoardDTO() {}

    public BoardDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
