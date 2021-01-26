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

    public User join(User user) throws Exception{
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.findByEmail(user.getEmail())
                .orElse(userRepository.save(user));
        return user;
    }

    public User findUserByEmail(String email) throws Exception{
        Optional<User> user = userRepository.findByEmail(email);
        Optional.ofNullable(user);
        return user.get();
    }

    public void fixUserInfo (String email, User fixInfo) throws Exception {
        Optional<User> user = userRepository.findByEmail(email);
        User fixUser = user.get();
        System.out.println(fixUser);
        fixUser.update(fixInfo.getName(), fixInfo.getPassword());
        System.out.println(fixUser);
    }

    public void deleteUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        userRepository.delete(user.get());
    }
}
