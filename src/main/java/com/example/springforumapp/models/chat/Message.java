package com.example.springforumapp.models.chat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "text")
    private String textOfMessage;

    @Column(name = "time")
    private LocalDate timeOfMessage;

    public Message () { }

    public Message(int id, String textOfMessage, LocalDate timeOfMessage) {
        this.id = id;
        this.textOfMessage = textOfMessage;
        this.timeOfMessage = timeOfMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextOfMessage() {
        return textOfMessage;
    }

    public void setTextOfMessage(String textOfMessage) {
        this.textOfMessage = textOfMessage;
    }

    public LocalDate getTimeOfMessage() {
        return timeOfMessage;
    }

    public void setTimeOfMessage(LocalDate timeOfMessage) {
        this.timeOfMessage = timeOfMessage;
    }
}
