package com.teamkph.kph.chat.dto;

import lombok.Getter;
import lombok.Setter;

//메시지 DTO
@Getter
public class ChatMessageDto {

    public enum MessageType {
        ENTER, TALK
    }
    private MessageType type;
    private String roomId;
    private String sender;

    @Setter
    private String message;
}
