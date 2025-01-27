package com.ameda.works.chat_oscaris.entities.common;


import com.ameda.works.chat_oscaris.entities.message.Message;
import com.ameda.works.chat_oscaris.media.Utility;
import com.ameda.works.chat_oscaris.responses.MessageResponse;
import org.springframework.stereotype.Service;

@Service
public class MessageMapper {


    public MessageResponse toMessageResponse(Message message) {
        return MessageResponse.builder()
                .id(message.getId())
                .content(message.getContent())
                .senderId(message.getSenderId())
                .receiverId(message.getReceiverId())
                .messageType(message.getMessageType())
                .messageState(message.getState())
                .createdAt(message.getCreatedDate())
                .media(Utility.readfile(message.getMediaFilePath()))
                .build();
    }
}
