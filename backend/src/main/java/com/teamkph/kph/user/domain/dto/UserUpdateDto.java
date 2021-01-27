package com.teamkph.kph.user.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateDto {

    private String name;
    private String password;

    @Builder
    public UserUpdateDto(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
