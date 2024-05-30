package com.example.STOMP_Alarm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String messageContent;

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
