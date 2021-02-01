package com.teamkph.kph.user.controller;

import com.teamkph.kph.user.dto.UserInfoDto;
import com.teamkph.kph.user.dto.UserSaveDto;
import com.teamkph.kph.user.dto.UserUpdateDto;
import com.teamkph.kph.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public UserSaveDto registration(@RequestBody @Valid UserSaveDto userSaveDto) throws Exception {
        return userService.join(userSaveDto);
    }

    @GetMapping("/user/{email}")
    public UserInfoDto findUserByEmail(@PathVariable("email") String email) throws Exception {
        return userService.findUserByEmail(email);
    }

    @ApiOperation(value="사용자 정보 수정", notes="특정 사용자의 정보를 수정합니다다.")
    @PatchMapping("/user/{email}")
    public void fixUserInfo(@PathVariable("email") String email, @RequestBody UserUpdateDto user) throws Exception {
        System.out.println(email);
        userService.fixUserInfo(email, user);
    }

    @ApiOperation(value="사용자 삭제", notes="사용자를 삭제합니다.")
    @DeleteMapping("/user/{email}")
    public void deleteUser(@PathVariable("email") String email) throws Exception {
        userService.deleteUser(email);
    }
}