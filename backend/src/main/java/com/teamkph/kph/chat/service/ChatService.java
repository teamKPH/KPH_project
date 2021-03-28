package com.teamkph.kph.chat.service;


import com.teamkph.kph.chat.dto.ChatRoomDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
   private  Map<String, ChatRoomDto> chatRoomMap;

   @PostConstruct
    private void init() {
       chatRoomMap = new LinkedHashMap<>();
   }

   public List<ChatRoomDto> findAllRoom() {
       //채팅방 생성순서 최근 순으로 반환
       List chatRooms = new ArrayList<>(chatRoomMap.values());
       Collections.reverse(chatRooms);
       return chatRooms;
   }

   public ChatRoomDto findRoomById(String id) {
       return chatRoomMap.get(id);
   }

   public ChatRoomDto createChatRoom(String name) {
       ChatRoomDto chatRoomDto = ChatRoomDto.create(name);
       chatRoomMap.put(chatRoomDto.getRoomId(), chatRoomDto);
       return chatRoomDto;
   }
}
