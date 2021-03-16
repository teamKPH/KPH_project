package com.teamkph.kph.registration.controller;

import com.teamkph.kph.registration.service.AuthService;
import com.teamkph.kph.user.dto.UserLoginDto;
import com.teamkph.kph.user.dto.UserSaveDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @ApiOperation(value="로그인", notes="사용자 로그인")
    @PostMapping("/signin/{email}/{password}")
    public UserLoginDto signin(@PathVariable("email") String email, @PathVariable("password") String password) throws Exception{
        return authService.login(email, password);
    }

    @ApiOperation(value="회원가입", notes="사용자 정보 등록")
    @PostMapping("/signup")
    public void registration(@RequestBody @Valid UserSaveDto userSaveDto) throws Exception {
       authService.join(userSaveDto);
    }
}
