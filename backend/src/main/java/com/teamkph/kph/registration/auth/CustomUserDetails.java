package com.teamkph.kph.registration.auth;

import com.teamkph.kph.chat.domain.ChatRoom;
import com.teamkph.kph.user.domain.User;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

    @Column(nullable = true)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private List<ChatRoom> chatRoom;

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Builder
    public CustomUserDetails(User user) {
        this.password = user.getPassword();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.chatRoom = user.getChatRoom();
    }

//    public User update(String name, String password) {
//        this.name = name;
//        this.password = password;
//        return this;
//    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String auth : role.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
