package com.example.springforumapp.files.models.domain;


import com.example.springforumapp.files.models.UpFileType;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.users.models.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "upfiles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpFile {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "url")
    private String url;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UpFileType type;

    @ManyToMany(mappedBy = "upfiles")
    private List<Publication> publications;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
