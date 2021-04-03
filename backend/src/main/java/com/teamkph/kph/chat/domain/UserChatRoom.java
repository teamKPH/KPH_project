package com.teamkph.kph.chat.domain;


import com.teamkph.kph.chat.domain.chatRoom.ChatRoom;
import com.teamkph.kph.user.domain.User;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class UserChatRoom {
    @Id
    @Column(name = "USERCHATROOM_ID")
    private String id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CHATROOM_ID")
    private ChatRoom chatRoom;
}
