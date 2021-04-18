package com.teamkph.kph.chat.domain.chatMessage;

import com.teamkph.kph.chat.domain.chatRoom.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
