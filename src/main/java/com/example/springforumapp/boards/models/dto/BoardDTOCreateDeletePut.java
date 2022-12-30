package com.example.springforumapp.boards.models.dto;

public class BoardDTOCreateDeletePut {
    private String name;

    public BoardDTOCreateDeletePut() {
    }

    public BoardDTOCreateDeletePut(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
