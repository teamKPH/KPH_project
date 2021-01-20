package com.teamkph.kph.user.controller;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

//    @DisplayName("회원가입 테스트")
//    @Test
//    public void signupTest() throws Exception {
//        Map<String, String> input = new HashMap<>();
//        input.put("name", "test2");
//        input.put("email", "test2@google.com");
//        input.put("password", "test2_password");
//
//        mockMvc.perform(post("/signup")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(input)))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }

//    @DisplayName("일반 로그인 테스트")
//    @Test
//    @WithMockUser(username = "mockUser", roles = {"ROLE_USER"})
//    public void loginTest() throws Exception {
////        mockMvc.perform(MockMvcRequestBuilders.get())
//    }


    @DisplayName("회원정보 조회")
    @Test
    public void findUserByEmailTest() throws Exception {

        User user = User.builder()
                .name("hello")
                .email("hello@google.com")
                .password("test_password")
                .role("ROLE_USER")
                .build();

        userRepository.save(user);

        User newUser = (User) mockMvc.perform(get("/user/test@google.com"));
        Assertions.assertThat(newUser.getName()).isEqualTo("hello");
    }


    @DisplayName("회원정보 수정")
    @Test
    public void fixUserInfo() throws Exception {
        User user = User.builder()
                .name("test")
                .email("test3@google.com")
                .password("test_password")
                .role("ROLE_USER")
                .build();

        userRepository.save(user);

        Map<String, String> input = new HashMap<>();
        input.put("name", "test100");
        input.put("password", "test100_password");

        mockMvc.perform(patch("/user/test3@gamil.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}