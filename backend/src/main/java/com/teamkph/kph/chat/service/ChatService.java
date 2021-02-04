package com.teamkph.kph.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamkph.kph.chat.domain.ChatRoom;
import com.teamkph.kph.chat.domain.ChatRoomRepository;
import com.teamkph.kph.chat.domain.MessageType;
import com.teamkph.kph.chat.dto.ChatMessageDto;
import com.teamkph.kph.chat.dto.ChatRoomDto;
import com.teamkph.kph.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ObjectMapper objectMapper;
    private final ChatRoomRepository repository;

//    public List<ChatRoomDto> findAllRoom() {
//        List chatRooms = new ArrayList<>(chatRoomMap.values());
//        Collections.reverse(chatRooms);
//        return chatRooms;
//    }

    public ChatRoomDto findRoomById(String id) throws Exception {
        Optional<ChatRoom> room = repository.findById(id);
        return new ChatRoomDto(room.get());
    }

    public void createChatRoom(String name, List<User> users) throws Exception {
        ChatRoomDto chatRoomDto = ChatRoomDto.create(name);
        //chatRoomMap.put(chatRoomDto.getRoomId(), chatRoomDto);    삭제할 부분
        //레포지토리 접근 필요


        repository.save()
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public String message(ChatMessageDto messageDto) {
        if(MessageType.ENTER.equals(messageDto.getType()))
            messageDto.setMessage(messageDto.getSender() + "님이 입장하셨습니다.");
        return "/sub/chat/room/" + messageDto.getRoomId();
    }
}
