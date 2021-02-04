package com.teamkph.kph.chat.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Chat {

    @Id
    private String roomId;

    @Column
    @Enumerated(EnumType.STRING)
    private MessageType type;

    @Column
    private String sender;

    @ManyToOne
    @JoinColumn(name = "CHATROOM_ID")
    private ChatRoom chatRoom;
}
