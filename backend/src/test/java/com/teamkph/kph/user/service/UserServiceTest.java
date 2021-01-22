package com.teamkph.kph.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamkph.kph.user.domain.User;
import com.teamkph.kph.user.domain.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest {

    @Autowired
    public UserRepository userRepository;


    @DisplayName("회원등록 테스트")
    @Test
    public void registerTest() throws Exception {
        User user = User.builder()
                .name("test")
                .email("test@google.com")
                .password("test_password")
                .role("ROLE_USER")
                .build();

        userRepository.save(user);

        Optional<User> findUser = userRepository.findByEmail("test@google.com");
        Assertions.assertThat(findUser.get().getName()).isEqualTo("test");
    }

}