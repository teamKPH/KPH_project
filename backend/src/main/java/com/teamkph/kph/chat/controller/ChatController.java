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
    public CommonResult sendMessage(ChatMessageDto message) {
        return chatService.sendMessage(message);
    }

    @PostMapping("/upload/{roomId}")
    @ResponseBody
    public CommonResult upload(@PathVariable Long roomId, @RequestParam("data")MultipartFile multipartFile) throws IOException {
        return chatService.upload(multipartFile, roomId);
    }

    @GetMapping("/list/{roomId}")
    @ResponseBody
    public CommonResult getFileList(@PathVariable Long roomId) throws IOException {
        return chatService.getFileList(roomId);
    }

    @PostMapping("/download/{roomId}")
    @ResponseBody
    public CommonResult downloadFile(@PathVariable Long roomId, @RequestParam("file")String fileName) throws IOException {
        return chatService.downloadFile(roomId, fileName);
    }
}
