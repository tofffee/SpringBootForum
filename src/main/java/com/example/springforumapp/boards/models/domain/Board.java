package com.example.springforumapp.boards.models.domain;

import com.example.springforumapp.publications.models.domain.Publication;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Boards")
public class Board {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "board")
    private List<Publication> publications;
    public Board() {}

    public Board(int id, String name, List<Publication> publications) {
        this.id = id;
        this.name = name;
        this.publications = publications;
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

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }
}
