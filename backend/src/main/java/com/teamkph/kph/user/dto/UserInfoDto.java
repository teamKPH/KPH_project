package com.teamkph.kph.user.dto;

import com.teamkph.kph.responseRole.CommonResult;
import com.teamkph.kph.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserInfoDto {

    private String name;

    private String email;

    private List<String> roles = new ArrayList<>();

    private List<ChatRoom> chatRoom;

    @Builder
    public UserInfoDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.chatRoom = user.getChatRoom();
        this.roles = user.getRoles();
    }

}
