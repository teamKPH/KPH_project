package com.teamkph.kph.chat.controller;

import com.teamkph.kph.chat.dto.ChatMessageDto;
import com.teamkph.kph.chat.dto.ChatRoomDto;
import com.teamkph.kph.chat.domain.MessageType;
import com.teamkph.kph.chat.service.ChatService;

import com.teamkph.kph.user.domain.User;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChatService chatService;

    private final SimpMessageSendingOperations messageSendingOperations;

    @PostMapping("/chat")
    public ChatRoomDto createChatRoom(@RequestParam String name, @RequestBody List<User> users) {
        return chatService.createChatRoom(name, users);
    }

    @PostMapping("/adduser")
    public void addUserToChatRoom(@RequestBody List<User> users) {
        chatService.addUserToChatRoom(users);
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessageDto messageDto) {
        String sub = chatService.message(messageDto);
        messageSendingOperations.convertAndSend(sub, messageDto);
    }


}
