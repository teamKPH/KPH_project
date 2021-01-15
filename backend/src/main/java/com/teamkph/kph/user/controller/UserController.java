package com.teamkph.kph.user.controller;

import com.teamkph.kph.user.domain.User;
import com.teamkph.kph.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void registration(User user) throws Exception {
        userService.join(user);
    }
}
