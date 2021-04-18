package com.teamkph.kph.chat.domain.chatMessage;

import com.teamkph.kph.chat.domain.chatRoom.ChatRoom;
import com.teamkph.kph.chat.dto.ChatMessageDto;
import lombok.Builder;
import lombok.Getter;
import com.teamkph.kph.chat.domain.MessageType;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue
    @Column(name = "CHAT_ID")
    private Long roomId;

    @Column
    @Enumerated(EnumType.STRING)
    private MessageType type;

    @Column
    private String sender;

    @Column
    private Date sendDate;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "CHATROOM_ID")
    private ChatRoom chatRoom;

    @Builder
    public ChatMessage(ChatMessageDto chatMessageDto) {
        this.roomId = chatMessageDto.getRoomId();
        this.type = chatMessageDto.getType();
        this.sender = chatMessageDto.getSender();
        this.sendDate = chatMessageDto.getSendDate();
        this.content = chatMessageDto.getContent();
    }

    public ChatMessage() {

    }
}
