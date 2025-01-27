package com.ameda.works.chat_oscaris.entities.notification;


import com.ameda.works.chat_oscaris.entities.enu.MessageType;
import com.ameda.works.chat_oscaris.entities.enu.NotificationType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {

    private String chatId;
    private String content;
    private String senderId;
    private String receiverId;
    private String chatName;
    private MessageType messageType;
    private NotificationType notificationType;
    private  byte [] media;
}
