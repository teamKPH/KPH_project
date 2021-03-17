package com.teamkph.kph.user.dto;

import com.teamkph.kph.responseRole.CommonResult;
import com.teamkph.kph.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class LoginResponseDto {

    private String token;

    private List<String> roles = new ArrayList<>();

    @Builder
    public LoginResponseDto(User user, String token) {
        this.token = token;
        this.roles = user.getRoles();
    }
}
