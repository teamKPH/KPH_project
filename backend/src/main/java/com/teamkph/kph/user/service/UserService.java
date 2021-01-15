package com.teamkph.kph.user.service;

import com.teamkph.kph.user.domain.User;
import com.teamkph.kph.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void join(User user) {
        userRepository.findByEmail(user.getEmail())
                .orElse(userRepository.save(user));
    }
}
