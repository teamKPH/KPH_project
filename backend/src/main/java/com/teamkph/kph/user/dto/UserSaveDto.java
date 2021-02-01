package com.teamkph.kph.user.dto;

import com.teamkph.kph.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UserSaveDto {

    @NotBlank
    @Length(min=3, max=30)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9_-]{3,20}$")
    private String name;

    @NotBlank
    @Length(min=8, max=40)
    private String password;

    @NotBlank
    @Email
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