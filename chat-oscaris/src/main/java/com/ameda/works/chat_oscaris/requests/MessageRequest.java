package com.ameda.works.chat_oscaris.requests;


import com.ameda.works.chat_oscaris.entities.enu.MessageType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MessageRequest {

    private String content;
    private String senderId;
    private String receiverId;
    private MessageType messageType;
    private String chatId;


}
