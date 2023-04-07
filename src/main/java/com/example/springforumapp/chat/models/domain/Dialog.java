package com.example.springforumapp.chat.models.domain;


import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.users.models.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "dialogs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Dialog {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_at")
    private LocalDate createdAt;


    @ManyToMany
    @JoinTable(
            name = "users_dialogs",
            joinColumns = @JoinColumn(name = "dialog_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "dialog")
    private List<ChatMessage> chatMessages;

    public void addUsers(Set<User> users) {
        this.users.addAll(users);
        users.forEach(user->{
            user.getDialogs().add(this);
        });
    }

}
