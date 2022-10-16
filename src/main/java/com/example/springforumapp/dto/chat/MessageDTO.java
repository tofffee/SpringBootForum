package com.example.springforumapp.dto.chat;

public class MessageDTO {

    private String textOfMessage;

    public MessageDTO () { }

    public MessageDTO(String textOfMessage)
    {
        this.textOfMessage = textOfMessage;
    }

    public String getTextOfMessage() {
        return textOfMessage;
    }

    public void setTextOfMessage(String textOfMessage) {
        this.textOfMessage = textOfMessage;
    }
}
