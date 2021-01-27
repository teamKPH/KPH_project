package com.teamkph.kph.user.controller;

import com.teamkph.kph.user.domain.User;
import com.teamkph.kph.user.domain.dto.UserInfoDto;
import com.teamkph.kph.user.domain.dto.UserSaveDto;
import com.teamkph.kph.user.domain.dto.UserUpdateDto;
import com.teamkph.kph.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public void registration(@RequestBody User user) throws Exception {
        userService.join(user);
    }

    @GetMapping("/user/{email}")
    public UserInfoDto findUserByEmail(@PathVariable("email") String email) throws Exception {
        return userService.findUserByEmail(email);
    }

    @PatchMapping("/user/{email}")
    public void fixUserInfo(@PathVariable("email") String email, @RequestBody UserUpdateDto user) throws Exception {
        System.out.println(email);
        userService.fixUserInfo(email, user);
    }

    @DeleteMapping("/user/{email}")
    public void deleteUser(@PathVariable("email") String email) throws Exception {
        userService.deleteUser(email);
    }
}
