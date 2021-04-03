package com.teamkph.kph.chat.service;


import com.teamkph.kph.chat.domain.chatMessage.ChatMessage;
import com.teamkph.kph.chat.domain.chatMessage.ChatMessageRepository;
import com.teamkph.kph.chat.domain.chatRoom.ChatRoom;
import com.teamkph.kph.chat.domain.chatRoom.ChatRoomRepository;
import com.teamkph.kph.chat.domain.MessageType;
import com.teamkph.kph.chat.dto.ChatMessageDto;
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

   private final ChatRoomRepository chatRoomRepository;
   private final ChatMessageRepository chatMessageRepository;

   @PostConstruct
    private void init() {
       chatRoomMap = new LinkedHashMap<>();
   }

   public List<ChatRoomDto> findAllRoom() {
       //채팅방 생성순서 최근 순으로 반환
//       List chatRooms = new ArrayList<>(chatRoomMap.values());

//       https://blog.naver.com/writer0713/221596629064 참고해서 변경
//       List<ChatRoom> chatRooms = chatRoomRepository.findAll();
//       return new List<ChatRoomDto>(chatRooms.addAll());
   }

   public ChatRoomDto findRoomById(Long id) {
//       return chatRoomMap.get(id);
       Optional<ChatRoom> chatRoom = chatRoomRepository.findById(id);
       return new ChatRoomDto(chatRoom.get());
   }

   public ChatRoomDto createChatRoom(String name) {
       ChatRoomDto chatRoomDto = ChatRoomDto.create(name);
       chatRoomMap.put(chatRoomDto.getRoomId(), chatRoomDto);
       return chatRoomDto;
   }

   public void sendMessage(ChatMessageDto message) {
       if(MessageType.ENTER.equals(message.getType()))
           message.setContent(message.getSender() + "님이 입장하셨습니다.");
       chatMessageRepository.save(new ChatMessage(message));
   }
}
