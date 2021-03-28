package com.teamkph.kph.chat.domain;

import com.teamkph.kph.chat.dto.ChatMessageDto;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Chat {

    @Id
    @Column(name = "CHAT_ID")
    private String roomId;

    @Column
    @Enumerated(EnumType.STRING)
    private ChatMessageDto.MessageType type;

    @Column
    private String sender;

    @ManyToOne
    @JoinColumn(name = "CHATROOM_ID")
    private ChatRoom chatRoom;
}
