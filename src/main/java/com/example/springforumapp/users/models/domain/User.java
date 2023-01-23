package com.example.springforumapp.users.models.domain;

import com.example.springforumapp.comments.models.domain.Comment;
import com.example.springforumapp.files.models.domain.UpFile;
import com.example.springforumapp.publications.models.domain.Publication;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;

    @Column(name = "username")
    @Size(min = 4 ,max = 15, message = "User error (username must be > 4 and < 15)")
    @NotNull
    private String username;

    @Column(name = "avatar_url")
    @NotNull
    private String avatarUrl;

    @Email
    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "password")
    @Size(min = 5 , message = "User error (Password must contain more than 5 symbols)")
    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @Column(name = "activation_code")
    private String activationCode;

    @Column(name = "enabled")
    @NotNull
    private Boolean enabled;

    @OneToMany(mappedBy = "user")
    private List<Publication> publications;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "sentTo")
    private List<com.example.springforumapp.email.models.domain.Email> emailMessages;

    @OneToMany(mappedBy = "user")
    private List<UpFile> upFiles;

}
