package com.example.springforumapp.users.models.domain;

import com.example.springforumapp.publications.models.domain.Publication;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    @Size(min = 4 ,max = 15, message = "User error (username must be > 4 and < 15)")
    private String username;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @Size(min = 5 , message = "User error (Password must contain more than 5 symbols)")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "activation_code")
    private String activationCode;
    @Column(name = "enabled")
    private Boolean enabled;

    @OneToMany(mappedBy = "user")
    private List<Publication> publications;
}
