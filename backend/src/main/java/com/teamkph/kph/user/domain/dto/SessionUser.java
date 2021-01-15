package com.teamkph.kph.user.domain.dto;

import com.teamkph.kph.user.domain.User;

import java.io.Serializable;

public class SessionUser implements Serializable {
    private String name;
    private String email;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
    }
}