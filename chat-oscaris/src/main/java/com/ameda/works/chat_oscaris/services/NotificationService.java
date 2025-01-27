package com.ameda.works.chat_oscaris.services;


import com.ameda.works.chat_oscaris.entities.notification.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void sendNotification (String userId, Notification notification){
        log.info("sending websocket notification to {} with payload {}",userId,notification);
        simpMessagingTemplate.convertAndSendToUser(userId, "/chat"+ userId, notification);
    }
}
