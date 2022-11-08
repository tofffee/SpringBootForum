package com.example.springforumapp.chat.models.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "chat_messages")
public class ChatMessage {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "text")
    private String textOfChatMessage;

    @Column(name = "time")
    private LocalDate timeOfChatMessage;

    public ChatMessage() { }

    public ChatMessage(int id, String textOfChatMessage, LocalDate timeOfChatMessage) {
        this.id = id;
        this.textOfChatMessage = textOfChatMessage;
        this.timeOfChatMessage = timeOfChatMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextOfChatMessage() {
        return textOfChatMessage;
    }

    public void setTextOfChatMessage(String textOfChatMessage) {
        this.textOfChatMessage = textOfChatMessage;
    }

    public LocalDate getTimeOfChatMessage() {
        return timeOfChatMessage;
    }

    public void setTimeOfChatMessage(LocalDate timeOfChatMessage) {
        this.timeOfChatMessage = timeOfChatMessage;
    }
}
