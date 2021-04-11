package com.teamkph.kph.user.dto;

import com.teamkph.kph.chat.domain.userChatRoom.UserChatRoom;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserChatRoomListUpdateDto {

    @NotBlank
    private UserChatRoom userChatRoom;

//    @Builder
//    public UserUpdateDto(User user) {
//        //this.userChatRoom;
//    }
}
