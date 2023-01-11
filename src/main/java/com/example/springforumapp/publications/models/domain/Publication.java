package com.example.springforumapp.publications.models.domain;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.comments.models.domain.Comment;
import com.example.springforumapp.files.models.domain.Image;
import com.example.springforumapp.users.models.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publications")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Publication {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Column(name = "name")
    @NotNull
    @Size(min = 1,message = "Publication error (name of publication is too small)")
    private String name;

    @Column(name = "text")
    @NotNull
    @Size(min = 1,message = "Publication error (text of publication is too small)")
    private String text;

    @Column(name = "date_of_creation")
    private LocalDate dateOfCreation;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    private Board board;

    @OneToMany(mappedBy = "publication")
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Publication_Image",
            joinColumns = @JoinColumn(name = "publication_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private List<Image> images;

//    public void addImage(Image image){
//        if(this.images == null){
//            this.images = new ArrayList<>();
//        }
//        this.images.add(image);
//        image.setPublication(this);
//    }
//
//    public void removeImage(Image image){
//        this.images.remove(image);
//        image.setPublication(null);
//    }
}
