package com.teamkph.kph.user.domain;

import com.teamkph.kph.chat.domain.ChatRoom;
import com.teamkph.kph.user.dto.UserUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @Column(nullable = true)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy = "user")
    private List<ChatRoom> chatRoom = new ArrayList<>();

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Builder
    public User(Long id, String password, String name, String email, String role) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public User update(UserUpdateDto user) {
        this.name = user.getName();
        this.password = user.getPassword();
        return this;
    }

    public void addChatRoom()

}
