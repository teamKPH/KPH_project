package com.teamkph.kph.chat.domain;

import com.teamkph.kph.user.domain.User;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class ChatRoom {

    @Id
    @Column(name = "CHATROOM_ID")
    private String roomId;
    @Column
    private String name;
    @Column
    private Integer userCount;

    @OneToMany(mappedBy = "chatRoom")
    private List<UserChatRoom> userChatRooms = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom")
    private List<Chat> chat = new ArrayList<>();

    public void update(ChatRoom chatRoom, UserChatRoom userChatRoom) {
        chatRoom.userChatRooms.add(userChatRoom);
        return;
    }
}
