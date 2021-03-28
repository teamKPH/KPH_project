package com.teamkph.kph.chat.controller;

import com.teamkph.kph.chat.domain.ChatRoom;
import com.teamkph.kph.chat.dto.ChatMessageDto;
import com.teamkph.kph.chat.dto.ChatRoomDto;
import com.teamkph.kph.chat.service.ChatService;

import com.teamkph.kph.user.domain.User;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    private final SimpMessageSendingOperations messagingTemplate;

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
//
//    // 채팅방 입장 화면
//    @GetMapping("/room/enter/{roomId}")
//    public String roomDetail(Model model, @PathVariable String roomId) {
//        model.addAttribute("roomId", roomId);
//        return "/chat/roomdetail";
//    }
//
//    //특정채팅방조회
//    @GetMapping("/room/{roomId}")
//    @ResponseBody
//    public ChatRoomDto roomInfo(@PathVariable String roomId) {
//        return chatService.findRoomById(roomId);
//    }

//    @PostMapping("/chat")
//    public void createChatRoom(@RequestParam String name, @RequestBody List<User> users) {
//        //chatService.createChatRoom(name, users);
//        return;
//    }

//    @PostMapping("/adduser")
//    public void addUserToChatRoom(@RequestBody List<User> users) {
//        //chatService.addUserToChatRoom(users);
//    }

    @MessageMapping("/message")
    public void message(ChatMessageDto message) {

        if (ChatMessageDto.MessageType.ENTER.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);

//        String sub = chatService.message(messageDto);
//        messageSendingOperations.convertAndSend(sub, messageDto);
    }

//    @GetMapping()
//    public List<ChatRoomDto> findAllRoomByEmail(String email) {
//
//    }

    //채팅방 정보 반환 API 생성해야함
}
