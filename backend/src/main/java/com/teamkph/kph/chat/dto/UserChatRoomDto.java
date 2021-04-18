package com.teamkph.kph.chat.dto;

import com.teamkph.kph.chat.domain.chatRoom.ChatRoom;
import com.teamkph.kph.chat.domain.userChatRoom.UserChatRoom;
import com.teamkph.kph.user.domain.User;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class UserChatRoomDto {

    private Long id;

    @NotBlank
    private User user;

    @NotBlank
    private ChatRoom chatRoom;

    @Builder
    public UserChatRoomDto(User user, ChatRoom chatRoom) {
        this.user = user;
        this.chatRoom = chatRoom;
    }

    public UserChatRoom toEntity() {
        return UserChatRoom.builder()
                .id(Long.parseLong(UUID.randomUUID().toString()))
                .user(this.user)
                .chatRoom(this.chatRoom)
                .build();
    }

}
