package com.teamkph.kph.registration.controller;

import com.teamkph.kph.advice.customException.CustomValidationException;
import com.teamkph.kph.registration.service.AuthService;
import com.teamkph.kph.user.dto.LoginRequestDto;
import com.teamkph.kph.user.dto.LoginResponseDto;
import com.teamkph.kph.user.dto.UserSaveDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
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
    public void registration(@ApiParam(value = "회원가입 요청 객체", required = true) @RequestBody @Valid UserSaveDto userSaveDto, Errors errors) {
       authService.join(userSaveDto, errors);
    }
}
