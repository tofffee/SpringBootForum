package com.example.springforumapp.users.models.domain;

import com.example.springforumapp.publications.models.domain.Publication;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Users")
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


    public User(){}

    public User(int id, String username, String avatarUrl, String email, String password, String role, String activationCode, Boolean enabled, List<Publication> publications) {
        this.id = id;
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.email = email;
        this.password = password;
        this.role = role;
        this.activationCode = activationCode;
        this.enabled = enabled;
        this.publications = publications;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }
}
