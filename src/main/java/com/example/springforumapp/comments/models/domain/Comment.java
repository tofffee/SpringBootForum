package com.example.springforumapp.comments.models.domain;

import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.users.models.domain.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "comments_id_seq_pk", sequenceName = "comments_id_seq_pk", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "comments_id_seq_pk")
    private long id;

    @Column(name = "text")
    @NotNull
    @Size(min = 1,message = "Comment error (text of publication is too small)")
    private String text;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "publication_id", referencedColumnName = "id")
    private Publication publication;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> childComments;
}