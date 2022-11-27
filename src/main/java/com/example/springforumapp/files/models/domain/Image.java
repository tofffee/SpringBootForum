package com.example.springforumapp.files.models.domain;

import javax.persistence.*;


@Entity
@Table(name = "images")
public class Image {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "url")
    private String url;

    public Image() {
    }

    public Image(int id, String url) {
        Id = id;
        this.url = url;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

