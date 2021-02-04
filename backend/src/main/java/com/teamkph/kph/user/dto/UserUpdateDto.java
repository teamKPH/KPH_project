package com.teamkph.kph.user.dto;

import com.teamkph.kph.chat.domain.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@NoArgsConstructor
public class UserUpdateDto {

    @NotBlank
    @Length(min=3, max=30)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z_0-9-]")
    private String name;

    @NotBlank
    @Length(min=8, max=40)
    private String password;

    @Builder
    public UserUpdateDto(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
