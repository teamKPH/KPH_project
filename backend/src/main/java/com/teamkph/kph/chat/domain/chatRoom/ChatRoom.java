package com.teamkph.kph.chat.domain.chatRoom;

import com.teamkph.kph.chat.domain.UserChatRoom;
import com.teamkph.kph.chat.domain.chatMessage.ChatMessage;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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
    @Column
    private Date createdDate;

    public ChatRoom() { }

    @Builder
    public ChatRoom(Long roomId, String name, Integer userCount, Date createdDate, List<UserChatRoom> userChatRooms, List<ChatMessage> chatMessage) {
        this.roomId = roomId;
        this.name = name;
        this.userCount = userCount;
        this.createdDate = createdDate;
        this.userChatRooms = userChatRooms;
        this.chatMessage = chatMessage;
    }

    @OneToMany(mappedBy = "chatRoom")
    private List<UserChatRoom> userChatRooms = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> chatMessage = new ArrayList<>();

    public void update(ChatRoom chatRoom, UserChatRoom userChatRoom) {
        chatRoom.userChatRooms.add(userChatRoom);
    }
}
