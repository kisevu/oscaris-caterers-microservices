package com.ameda.works.chat_oscaris.repositories;

import com.ameda.works.chat_oscaris.entities.common.constants.MessageConstants;
import com.ameda.works.chat_oscaris.entities.enu.MessageState;
import com.ameda.works.chat_oscaris.entities.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,String> {

    @Query(name = MessageConstants.FIND_MESSAGES_BY_CHAT_ID)
    List<Message> findMessagesByChatId(@Param("chatId") String chatId);

    @Query(name = MessageConstants.SET_MESSAGES_TO_SEEN_BY_CHAT)
    @Modifying
    void setMessagesToSeenByChatId(@Param("chatId") String chatId,
                                   @Param("newState") MessageState messageState);
}
