package com.teamkph.kph.user.service;

import com.teamkph.kph.chat.dto.ChatRoomDto;
import com.teamkph.kph.responseRole.CommonResult;
import com.teamkph.kph.user.domain.User;
import com.teamkph.kph.user.domain.UserRepository;
import com.teamkph.kph.user.dto.UserChatRoomListUpdateDto;
import com.teamkph.kph.user.dto.UserInfoDto;
import com.teamkph.kph.user.dto.UserSaveDto;
import com.teamkph.kph.user.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

//    @Transactional(readOnly = true)
// //    @AuthenticationPrincipal
// //    Authentication authentication
//    public CommonResult findUserByEmail() throws Exception {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//        Optional<User> user = userRepository.findByEmail(email);

//        return new CommonResult('Success', new UserInfoDto(user.get()));
//    }

    @Transactional
    public void fixUserInfo(String email, UserUpdateDto fixInfo) throws Exception {
        Optional<User> user = userRepository.findByEmail(email);
        User fixUser = user.get();
        fixUser.update(fixInfo);
    }

    @Transactional
    public void deleteUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        userRepository.delete(user.get());
    }

//    @Transactional
//    public void addChatRoom(User updateUser, ChatRoomDto chatRoomDto) {
//        //User fixuser = userRepository.findByEmail(user.getEmail()).get()
//
//        .update(updateUser, chatRoomDto.getRoomId());
////        fixUser.update(fixInfo);
//    }
}