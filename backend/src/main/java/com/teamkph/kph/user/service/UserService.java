package com.teamkph.kph.user.service;

import com.teamkph.kph.user.domain.User;
import com.teamkph.kph.user.domain.UserRepository;
import com.teamkph.kph.user.domain.dto.UserInfoDto;
import com.teamkph.kph.user.domain.dto.UserSaveDto;
import com.teamkph.kph.user.domain.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

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