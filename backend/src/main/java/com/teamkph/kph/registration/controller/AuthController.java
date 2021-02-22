package com.teamkph.kph.registration.controller;

import com.teamkph.kph.registration.service.AuthService;
import com.teamkph.kph.user.dto.UserSaveDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private static AuthService authService;

//    @ApiOperation(value="로그인", notes="사용자 로그인")
//    @PostMapping("/signin")

    @ApiOperation(value="회원가입", notes="사용자 정보 등록")
    @PostMapping("/signup")
    public UserSaveDto registration(@RequestBody @Valid UserSaveDto userSaveDto) throws Exception {
        return authService.join(userSaveDto);
    }
}
