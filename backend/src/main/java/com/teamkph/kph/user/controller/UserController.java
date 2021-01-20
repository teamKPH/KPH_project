package com.teamkph.kph.user.controller;

import com.teamkph.kph.user.domain.User;
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
    public User findUserByEmail(@RequestParam String email) throws Exception {
        return userService.findUserByEmail(email);
    }

    @PatchMapping("/user/{email}")
    public void fixUserInfo(@RequestParam String email, @RequestBody User user) throws Exception {
        userService.fixUserInfo(email, user);
    }
}
