package com.teamkph.kph.user.domain.dto;

import com.teamkph.kph.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;


public class SessionUser implements Serializable {
    private String name;
    private String password;
    private String email;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public SessionUser(User user) {
        this.name = user.getName();
        this.password = encrypt(user.getPassword());
        this.email = user.getEmail();
    }

    private String encrypt(String email) {
        return passwordEncoder.encode(email);
    }
}