package com.teamkph.kph.chat.dto;

import com.teamkph.kph.chat.domain.Chat;
import com.teamkph.kph.chat.domain.MessageType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//메시지 DTO
@Getter
public class ChatMessageDto {
    private String roomId;
    private MessageType type;
    private String sender;

    @Setter
    private String message;

    @Builder
    public ChatMessageDto(Chat chat) {
        this.roomId = chat.getRoomId();
        this.type = chat.getType();
        this.sender = chat.getSender();
    }
}
