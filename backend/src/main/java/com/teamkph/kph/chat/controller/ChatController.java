package com.teamkph.kph.chat.controller;

import com.teamkph.kph.chat.dto.ChatMessageDto;
import com.teamkph.kph.chat.dto.ChatRoomDto;
import com.teamkph.kph.chat.service.ChatService;

import com.teamkph.kph.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;


    //채팅 리스트
    @GetMapping("/room")
    public String rooms(Model model) {
        return "chat/room";
    }

    //모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoomDto> room() {
        return chatService.findAllRoom();
    }

    //채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoomDto createRoom(@RequestParam String name) {
        return chatService.createChatRoom(name);
    }



//    @PostMapping("/chat")
//    public void createChatRoom(@RequestParam String name, @RequestBody List<User> users) {
//        //chatService.createChatRoom(name, users);
//        return;
//    }

 // 특정채팅방조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoomDto roomInfo(@PathVariable Long roomId) {
        return chatService.findRoomById(roomId);
    }

//    @PutMapping("/adduser")
//    public void addUserToChatRoom(@RequestBody List<User> users) {
//        //chatService.addUserToChatRoom(users);
//    }

    @MessageMapping("/message")
    public void sendMessage(ChatMessageDto message) {
        chatService.sendMessage(message);
    }

}
