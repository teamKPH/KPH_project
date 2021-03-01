package com.teamkph.kph.user.dto;

import com.teamkph.kph.user.domain.User;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class UserLoginDto {

    private String token;

    private List<String> roles = new ArrayList<>();

    @Builder
    public UserLoginDto(User user, String token) {
        this.token = token;
        this.roles = user.getRoles();
    }
}
