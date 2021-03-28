package com.teamkph.kph.user.dto;

import com.teamkph.kph.chat.domain.UserChatRoom;
import com.teamkph.kph.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserChatRoomListUpdateDto {

    @NotBlank
    private UserChatRoom userChatRoom;

//    @Builder
//    public UserUpdateDto(User user) {
//        //this.userChatRoom;
//    }
}
