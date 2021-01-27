package com.teamkph.kph.registration.auth;

import com.teamkph.kph.user.domain.User;
import com.teamkph.kph.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new CustomUserDetails (userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email)));
    }
}
