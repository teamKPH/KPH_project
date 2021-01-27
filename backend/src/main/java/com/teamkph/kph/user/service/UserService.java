package com.teamkph.kph.user.service;

import com.teamkph.kph.user.domain.User;
import com.teamkph.kph.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void join(User user) throws Exception{
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.findByEmail(user.getEmail())
                .orElse(userRepository.save(user));
    }

    @Transactional
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

    public void deleteUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        userRepository.delete(user.get());
    }
}
