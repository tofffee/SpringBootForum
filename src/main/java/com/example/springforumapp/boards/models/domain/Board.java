package com.example.springforumapp.boards.models.domain;

import com.example.springforumapp.publications.models.domain.Publication;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "boards")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Board {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "board")
    private List<Publication> publications;
}
