package com.example.springforumapp.files.models.domain;

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
    private int Id;

    @Column(name = "url")
    private String url;
}

