package com.teamkph.kph.chat.domain.chatRoom;

import com.teamkph.kph.chat.domain.UserChatRoom;
import com.teamkph.kph.chat.domain.chatMessage.ChatMessage;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class ChatRoom {

    @Id
    @GeneratedValue
    @Column(name = "CHATROOM_ID")
    private Long roomId;
    @Column
    private String name;
    @Column
    private Integer userCount;

    @OneToMany(mappedBy = "chatRoom")
    private List<UserChatRoom> userChatRooms = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> chatMessage = new ArrayList<>();

    public void update(ChatRoom chatRoom, UserChatRoom userChatRoom) {
        chatRoom.userChatRooms.add(userChatRoom);
        return;
    }
}
