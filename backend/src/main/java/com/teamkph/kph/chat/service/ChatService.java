package com.teamkph.kph.chat.service;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private final AmazonS3Client amazonS3Client;

    @Value("{cloud.aws.s3.bucket}")
    private String bucket;

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

    // S3 File upload
//    public String upload(MultipartFile multipartFile, String dir) {
//
//        File convertFile = new File(multipartFile.getOriginalFilename());
//        if(convertFile.createNewFile()) {
//            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
//                fos.write(file.getBytes());
//            }
//            return Optional.of(convertFile);
//        }
//
//        return Optional.empty();
//
//        File uploadFile = convert(multipartFile)
//                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));
//
//
//        String fileName = dir + "/" + uploadFile.getName();
//        amazonS3Client.putObject(
//                new PutObjectRequest(bucket, fileName, uploadFile)
//                        .withCannedAcl(CannedAccessControlList.PublicRead));
//        String uploadImageUrl = amazonS3Client.getUrl(bucket, fileName).toString();
//
//        // 기존 로컬에 저장된 파일 삭제
//        uploadFile.delete();
//
//        return uploadImageUrl;
//    }

    public String upload(MultipartFile multipartFile, String chatRoomId) {
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));

        return upload(uploadFile, chatRoomId);
    }

    private String upload(File uploadFile, String chatRoomId) {
        String fileName = "upload/" + chatRoomId + "/" + uploadFile.getName();
        String uploadS3Url = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        return uploadS3Url;
    }

    private String putS3(File uploadFile, String fileName) {
        System.out.println(amazonS3Client);
        System.out.println(bucket);
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.Private));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    private Optional<File> convert(MultipartFile file) {
        File convertFile = new File(file.getOriginalFilename());
        try {
            convertFile.createNewFile();
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
