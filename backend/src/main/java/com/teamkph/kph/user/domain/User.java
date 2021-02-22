package com.teamkph.kph.user.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.teamkph.kph.user.dto.UserUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = true)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();



    @Builder
    public User(Long id, String password, String name, String email, List<String> roles) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public User update(UserUpdateDto user) {
        this.name = user.getName();
        this.password = user.getPassword();
        return this;
    }

}
