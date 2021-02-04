package com.teamkph.kph.chat.domain;

import com.teamkph.kph.user.domain.User;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class ChatRoom {

    @Id
    @Column(name = "CHATROOM_ID")
    private String roomId;
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "chatroom")
    private List<Chat> chat = new ArrayList<>();

}
