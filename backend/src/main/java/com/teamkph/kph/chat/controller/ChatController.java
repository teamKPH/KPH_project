package com.teamkph.kph.chat.controller;

import com.teamkph.kph.chat.dto.ChatMessageDto;
import com.teamkph.kph.chat.dto.ChatRoomDto;
import com.teamkph.kph.chat.domain.MessageType;
import com.teamkph.kph.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChatController {

    //private final ChatService chatService;

    private final SimpMessageSendingOperations messageSendingOperations;

    @MessageMapping("/chat/message")
    public void message(ChatMessageDto messageDto) {
        if(ChatMessageDto.MessageType.ENTER.equals(messageDto.getType()))
            messageDto.setMessage(messageDto.getSender() + "님이 입장하셨습니다.");
        messageSendingOperations.convertAndSend("/sub/chat/room/" + messageDto.getRoomId(), messageDto);
    }
}
