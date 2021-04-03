package com.teamkph.kph.chat.domain.chatRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

}
