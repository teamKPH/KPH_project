package com.teamkph.kph.registration.controller;

import com.teamkph.kph.registration.service.AuthService;
import com.teamkph.kph.user.dto.LoginRequestDto;
import com.teamkph.kph.user.dto.LoginResponseDto;
import com.teamkph.kph.user.dto.UserSaveDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @ApiOperation(value="로그인", notes="사용자 로그인")
    @PostMapping("/signin")
    public LoginResponseDto signin(@RequestBody @Valid LoginRequestDto dto) throws Exception{
        return authService.login(dto.getEmail(), dto.getPassword());
    }

    @ApiOperation(value="회원가입", notes="사용자 정보 등록")
    @PostMapping("/signup")
    public void registration(@RequestBody @Valid UserSaveDto userSaveDto) throws Exception {
       authService.join(userSaveDto);
    }
}
