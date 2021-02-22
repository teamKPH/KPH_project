package com.teamkph.kph.registration.service;

import com.teamkph.kph.user.domain.UserRepository;
import com.teamkph.kph.user.dto.UserSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserSaveDto join(UserSaveDto userSaveDto) throws Exception{
        String rawPassword = userSaveDto.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        userSaveDto.setPassword(encPassword);
        userRepository.findByEmail(userSaveDto.getEmail())
                .orElse(userRepository.save(userSaveDto.toEntity()));
        return userSaveDto;
    }
}
