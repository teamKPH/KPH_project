package com.teamkph.kph.chat.controller;

import com.teamkph.kph.chat.dto.ChatMessageDto;
import com.teamkph.kph.chat.dto.ChatRoomDto;
import com.teamkph.kph.chat.service.ChatService;

import com.teamkph.kph.responseRole.CommonResult;
import com.teamkph.kph.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
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

 // 특정채팅방조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoomDto roomInfo(@PathVariable Long roomId) {
        return chatService.findRoomById(roomId);
    }

    @PutMapping("/adduser/{id}")
    public void addUserToChatRoom(@PathVariable Long id, @RequestBody List<User> users) {
        chatService.addUserToChatRoom(id, users);
    }

    @MessageMapping("/message")
    public void sendMessage(ChatMessageDto message) {
        chatService.sendMessage(message);
    }


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("data")MultipartFile multipartFile) throws IOException {
        return chatService.upload(multipartFile, "static");
    }

    @GetMapping("/list/{id}")
    @ResponseBody
    public CommonResult getFileList(@PathVariable Long id) throws IOException {
        return chatService.getFileList(id);
    }

//    @PostMapping()
//    @ResponseBody
//    public CommonResult downloadFile(@PathVariable Long id, String fileName) throws IOException {
//        return chatService.downloadFile(id, fileName);
//    }
}
