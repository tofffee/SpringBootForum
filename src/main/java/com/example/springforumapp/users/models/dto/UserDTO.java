package com.example.springforumapp.users.models.dto;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class UserDTO {

    private int id;

    private String username;

    private String avatarUrl;

    public UserDTO() { }

    public UserDTO(int id, String username, String avatarUrl) {
        this.id = id;
        this.username = username;
        this.avatarUrl = avatarUrl;
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
}
