package com.teamkph.kph.chat.dto;

//채팅방 DTO

import com.teamkph.kph.chat.domain.chatRoom.ChatRoom;
import com.teamkph.kph.chat.domain.UserChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class ChatRoomDto {
    private Long roomId;
    private String name;
    private List<UserChatRoom> userChatRooms;
    private Integer userCount;
    private Date createdDate;

    public static ChatRoomDto save(String name) {
        ChatRoomDto chatRoomDto = new ChatRoomDto();
//        chatRoomDto.roomId = Long.parseLong(UUID.randomUUID().toString());
        chatRoomDto.name = name;
//        chatRoomDto.userCount = 1;
//        chatRoomDto.createdDate = new Date(System.currentTimeMillis());
        return chatRoomDto;
    }

    @Builder
    public ChatRoomDto(ChatRoom chatRoom) {
        this.roomId = chatRoom.getRoomId();
        this.name = chatRoom.getName();
        this.userCount = chatRoom.getUserCount();
        this.createdDate = chatRoom.getCreatedDate();
    }

    public ChatRoom toEntity() {
        return ChatRoom.builder()
                .roomId(Long.parseLong(UUID.randomUUID().toString()))
                .name(name)
                .userCount(1)
                .createdDate(new Date(System.currentTimeMillis()))
                .userChatRooms(new ArrayList<>())
                .chatMessage(new ArrayList<>())
                .build();
    }
}
