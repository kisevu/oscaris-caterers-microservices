package com.ameda.works.chat_oscaris.services;

import com.ameda.works.chat_oscaris.entities.chat.Chat;
import com.ameda.works.chat_oscaris.entities.common.ChatMapper;
import com.ameda.works.chat_oscaris.entities.user.User;
import com.ameda.works.chat_oscaris.repositories.ChatRepository;
import com.ameda.works.chat_oscaris.repositories.UserRepository;
import com.ameda.works.chat_oscaris.responses.ChatResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;
    private  final UserRepository userRepository;


    @Transactional(readOnly = true)
    public List<ChatResponse> getchatsByReceiverId(Authentication currentUser){
        final String userId =  currentUser.getName();
        return chatRepository.findChatsBySenderId(userId).stream()
                .map(c -> chatMapper.toChatMapper(c,userId))
                .toList();

    }

    public String createChat(String senderId, String receiverId ){
        Optional<Chat> existingChat = chatRepository.findChatByReceiverAndSender(senderId,receiverId);

        if ( existingChat.isPresent() ){
            return existingChat.get().getId();
        }

        User sender = userRepository.findByPublicId(senderId)
                .orElseThrow(()-> new EntityNotFoundException("could not find the sender with ID: "+ senderId));

        User receiver = userRepository.findByPublicId(receiverId)
                .orElseThrow(()-> new EntityNotFoundException("could not find the receiver with ID: "+ receiverId));


        Chat chat = new Chat();
        chat.setSender(sender);
        chat.setRecipient(receiver);

    }
}
