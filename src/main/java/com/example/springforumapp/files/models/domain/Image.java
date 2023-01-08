package com.example.springforumapp.files.models.domain;

import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.users.models.domain.User;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "images")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Image {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "publication_id", referencedColumnName = "id")
    private Publication publication;
}

