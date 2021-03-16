package com.teamkph.kph.registration.service;

import com.teamkph.kph.registration.auth.JwtTokenProvider;
import com.teamkph.kph.user.domain.User;
import com.teamkph.kph.user.domain.UserRepository;
import com.teamkph.kph.user.dto.UserLoginDto;
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

    private final JwtTokenProvider jwtTokenProvider;

    public UserLoginDto login(String email, String password) throws Exception {
//        try{
        User user = userRepository.findByEmail(email).orElseThrow();
        if (passwordEncoder.matches(password, user.getPassword())) {
            String token = jwtTokenProvider.createToken(email, user.getRoles());
            return new UserLoginDto(user, token);
        } else {
            throw null;
        }
        //throw Exception;

//        } catch(Exception e) {
//
//        }
    }

    @Transactional
    public void join(UserSaveDto userSaveDto) throws Exception {
        String rawPassword = userSaveDto.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        userSaveDto.setPassword(encPassword);
        userRepository.findByEmail(userSaveDto.getEmail())
                .orElse(userRepository.save(userSaveDto.toEntity()));
        return;
    }
}
