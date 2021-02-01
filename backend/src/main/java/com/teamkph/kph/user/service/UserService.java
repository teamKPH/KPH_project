package com.teamkph.kph.user.service;

import com.teamkph.kph.user.domain.User;
import com.teamkph.kph.user.domain.UserRepository;
import com.teamkph.kph.user.dto.UserInfoDto;
import com.teamkph.kph.user.dto.UserSaveDto;
import com.teamkph.kph.user.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;

//    private List<String> validCheck(UserSaveDto userSaveDto, Errors errors) {
//        List<String> errorList = new ArrayList<>();
//
//    }

    @Transactional
    public UserSaveDto join(UserSaveDto userSaveDto) throws Exception{
        userSaveDto.setRole("ROLE_USER");
        String rawPassword = userSaveDto.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        userSaveDto.setPassword(encPassword);
        userRepository.findByEmail(userSaveDto.getEmail())
                .orElse(userRepository.save(userSaveDto.toEntity()));
        return userSaveDto;
    }

    @Transactional(readOnly = true)
    public UserInfoDto findUserByEmail(String email) throws Exception{
        Optional<User> user = userRepository.findByEmail(email);
        return new UserInfoDto(user.get());
    }

    @Transactional
    public void fixUserInfo (String email, UserUpdateDto fixInfo) throws Exception {
        Optional<User> user = userRepository.findByEmail(email);
        User fixUser = user.get();
        fixUser.update(fixInfo);
    }

    @Transactional
    public void deleteUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        userRepository.delete(user.get());
    }
}