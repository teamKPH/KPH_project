package com.teamkph.kph.user.dto;

import com.teamkph.kph.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class UserInfoDto {

    private String name;

    private String email;

    private String role;

    @Builder
    public UserInfoDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

}
