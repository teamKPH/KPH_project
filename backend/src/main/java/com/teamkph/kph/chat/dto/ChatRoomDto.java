package com.teamkph.kph.chat.dto;

//채팅방 DTO

import com.teamkph.kph.chat.domain.ChatRoom;
import com.teamkph.kph.chat.domain.UserChatRoom;
import com.teamkph.kph.chat.service.ChatService;
import com.teamkph.kph.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class ChatRoomDto {
    private String roomId;
    private String name;
    private List<UserChatRoom> userChatRooms;
    private Integer userCount;

    public static ChatRoomDto create(String name) {
        ChatRoomDto chatRoomDto = new ChatRoomDto();
        chatRoomDto.roomId = UUID.randomUUID().toString();
        chatRoomDto.name = name;
        chatRoomDto.userCount = 1;
        return chatRoomDto;
    }

    @Builder
    public ChatRoomDto(ChatRoom chatRoom) {
        this.roomId = chatRoom.getRoomId();
        this.name = chatRoom.getName();
        this.userCount = chatRoom.getUserCount();
    }
}
