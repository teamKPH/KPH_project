package com.teamkph.kph.chat.dto;

import com.teamkph.kph.chat.domain.chatMessage.ChatMessage;
import com.teamkph.kph.chat.domain.MessageType;
import com.teamkph.kph.chat.domain.chatRoom.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

//메시지 DTO
@Getter
public class ChatMessageDto {
    private Long roomId;

    private MessageType type;
    private String sender;

    private Date sendDate;

    @Setter
    private String content;

    private ChatRoom chatRoom;

    @Builder
    public ChatMessageDto(ChatMessage chatMessage) {
        this.roomId = chatMessage.getRoomId();
        this.type = chatMessage.getType();
        this.sender = chatMessage.getSender();
        this.content = chatMessage.getContent();
        this.chatRoom = chatMessage.getChatRoom();
        this.sendDate = chatMessage.getSendDate();
    }

}
