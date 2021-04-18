package com.teamkph.kph.chat.service;


import com.teamkph.kph.chat.domain.chatMessage.ChatMessage;
import com.teamkph.kph.chat.domain.chatMessage.ChatMessageRepository;
import com.teamkph.kph.chat.domain.chatRoom.ChatRoom;
import com.teamkph.kph.chat.domain.chatRoom.ChatRoomRepository;
import com.teamkph.kph.chat.domain.MessageType;
import com.teamkph.kph.chat.domain.userChatRoom.UserChatRoomRepository;
import com.teamkph.kph.chat.dto.ChatMessageDto;
import com.teamkph.kph.chat.dto.ChatRoomDto;
import com.teamkph.kph.chat.dto.UserChatRoomDto;
import com.teamkph.kph.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
//   private  Map<String, ChatRoomDto> chatRoomMap;

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserChatRoomRepository userChatRoomRepository;
    private final SimpMessageSendingOperations messagingTemplate;

//   @PostConstruct
//    private void init() {
//       chatRoomMap = new LinkedHashMap<>();
//   }

    @Transactional
    public List<ChatRoomDto> findAllRoom() {
        //채팅방 생성순서 최근 순으로 반환

        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        List<ChatRoomDto> chatRoomDtos = new ArrayList<>();
        for (int i = 0; i < chatRooms.size(); i++) {
            chatRoomDtos.add(new ChatRoomDto(chatRooms.get(i)));
        }
        return chatRoomDtos;
    }


    @Transactional
    public ChatRoomDto createChatRoom(String name) {
        ChatRoomDto chatRoomDto = ChatRoomDto.save(name);
        chatRoomRepository.save(chatRoomDto.toEntity());
//       chatRoomMap.put(chatRoomDto.getRoomId(), chatRoomDto);
        return chatRoomDto;
    }

    @Transactional
    public ChatRoomDto findRoomById(Long id) {
//       return chatRoomMap.get(id);
        Optional<ChatRoom> chatRoom = chatRoomRepository.findById(id);
        return new ChatRoomDto(chatRoom.get());
    }

    public void addUserToChatRoom(Long id, List<User> users) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).get();
        //UserChatRoom 생성 및 ChatRoom, User에 UserChatRoom 추가
        for(User user : users) {
            UserChatRoomDto userChatRoomDto = new UserChatRoomDto(user, chatRoom);
            userChatRoomRepository.save(userChatRoomDto.toEntity());
            chatRoom.update(userChatRoomDto.toEntity());
            user.update(userChatRoomDto.toEntity());
        }

    }

    public void sendMessage(ChatMessageDto message) {
        if (MessageType.ENTER.equals(message.getType()))
            message.setContent(message.getSender() + "님이 입장하셨습니다.");
        chatMessageRepository.save(new ChatMessage(message));
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }


}
