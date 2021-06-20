package com.teamkph.kph.registration.service;

import com.teamkph.kph.advice.customException.CustomUserException;
import com.teamkph.kph.advice.customException.CustomValidationException;
import com.teamkph.kph.registration.auth.JwtTokenProvider;
import com.teamkph.kph.user.domain.User;
import com.teamkph.kph.user.domain.UserRepository;
import com.teamkph.kph.user.dto.LoginResponseDto;
import com.teamkph.kph.user.dto.UserSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponseDto login(String email, String password){
        User user = userRepository.findByEmail(email).orElseThrow(CustomUserException::new);
        if (passwordEncoder.matches(password, user.getPassword())) {
            String token = jwtTokenProvider.createToken(email, user.getRoles());
            return new LoginResponseDto(user, token);
        } else {
            throw new CustomUserException();
        }
    }

    @Transactional
    public void join(UserSaveDto userSaveDto, Errors errors) {
        if(errors.hasErrors()) throw new CustomValidationException();
        String rawPassword = userSaveDto.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        userSaveDto.setPassword(encPassword);
        if(userRepository.findByEmail(userSaveDto.getEmail()).isPresent()) throw new CustomValidationException("email");
        else userRepository.save(userSaveDto.toEntity());
    }
}
