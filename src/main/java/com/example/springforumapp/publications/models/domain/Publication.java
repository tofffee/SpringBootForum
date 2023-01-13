package com.example.springforumapp.publications.models.domain;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.comments.models.domain.Comment;
import com.example.springforumapp.files.models.domain.UpFile;
import com.example.springforumapp.users.models.domain.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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

    @ManyToMany
    @JoinTable(
            name = "publications_upfiles",
            joinColumns = @JoinColumn(name = "publication_id"),
            inverseJoinColumns = @JoinColumn(name = "upfile_id")
    )
    private List<UpFile> upfiles;

}
