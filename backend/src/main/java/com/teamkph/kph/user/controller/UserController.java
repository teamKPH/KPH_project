package com.teamkph.kph.user.controller;

import com.teamkph.kph.user.dto.UserInfoDto;
import com.teamkph.kph.user.dto.UserSaveDto;
import com.teamkph.kph.user.dto.UserUpdateDto;
import com.teamkph.kph.user.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token",
                required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "로그인 유저 정보 반환", notes = "현재 로그인된 유저의 정보를 반환합니다.")
    @GetMapping("/userinfo")
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token",
//                    required = true, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value="사용자 정보 조회", notes="특정 사용자 정보를 조회합니다.")
//    @GetMapping("/user")
//    public ResponseEntity<UserInfoDto> findUserByEmail() throws Exception {
//        return userService.findUserByEmail();
//    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value="사용자 정보 수정", notes="특정 사용자의 정보를 수정합니다.")
    @PatchMapping("/user/{email}")
    public void fixUserInfo(@PathVariable("email") String email, @RequestBody @Valid UserUpdateDto user) throws Exception {
        userService.fixUserInfo(email, user);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value="사용자 삭제", notes="사용자를 삭제합니다.")
    @DeleteMapping("/user/{email}")
    public void deleteUser(@PathVariable("email") String email) throws Exception {
        userService.deleteUser(email);
    }
}