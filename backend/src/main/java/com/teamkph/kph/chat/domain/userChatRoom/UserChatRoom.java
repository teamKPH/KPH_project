package com.teamkph.kph.chat.domain.userChatRoom;


import com.teamkph.kph.chat.domain.chatMessage.ChatMessage;
import com.teamkph.kph.chat.domain.chatRoom.ChatRoom;
import com.teamkph.kph.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Entity
public class UserChatRoom {
    @Id
    @GeneratedValue
    @Column(name = "USERCHATROOM_ID")
    private Long id;
//    private String id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CHATROOM_ID")
    private ChatRoom chatRoom;

    public UserChatRoom() {}

    @Builder
    public UserChatRoom(Long id, User user, ChatRoom chatRoom) {
        this.id = id;
        this.user = user;
        this.chatRoom = chatRoom;
    }

}
