package com.teamkph.kph.user.domain.dto;

import com.teamkph.kph.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveDto {

    private String name;
    private String password;
    private String email;
    private String role;

    @Builder
    public UserSaveDto(String name, String password, String email, String role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .role("ROLE_USER")
                .build();
    }
}