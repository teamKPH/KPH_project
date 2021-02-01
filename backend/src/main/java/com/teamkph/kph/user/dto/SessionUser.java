package com.teamkph.kph.user.dto;

import com.teamkph.kph.user.domain.User;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import java.io.Serializable;
public class SessionUser implements Serializable {


    @NotBlank
    @Length(min=3, max=30)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z_0-9-]")
    private String name;

    @NotBlank
    @Length(min=8, max=40)
    private String password;

    @NotBlank
    @Email
    private String email;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Builder
    public SessionUser(User user) {
        this.name = user.getName();
        this.password = encrypt(user.getPassword());
        this.email = user.getEmail();
    }

    private String encrypt(String email) {
        return passwordEncoder.encode(email);
    }
}