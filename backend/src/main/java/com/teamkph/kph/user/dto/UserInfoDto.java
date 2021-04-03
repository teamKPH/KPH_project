package com.teamkph.kph.user.dto;

import com.teamkph.kph.chat.domain.UserChatRoom;
import com.teamkph.kph.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserInfoDto {

    private String name;

    private String email;

    private List<String> roles = new ArrayList<>();

    private List<UserChatRoom> userChatRooms = new ArrayList<>();

    @Builder
    public UserInfoDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.userChatRooms = user.getUserChatRooms();
        this.roles = user.getRoles();
    }

}
