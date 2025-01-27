package com.ameda.works.chat_oscaris.services;

import com.ameda.works.chat_oscaris.entities.chat.Chat;
import com.ameda.works.chat_oscaris.entities.common.MessageMapper;
import com.ameda.works.chat_oscaris.entities.enu.MessageState;
import com.ameda.works.chat_oscaris.entities.enu.MessageType;
import com.ameda.works.chat_oscaris.entities.message.Message;
import com.ameda.works.chat_oscaris.media.FileService;
import com.ameda.works.chat_oscaris.repositories.ChatRepository;
import com.ameda.works.chat_oscaris.repositories.MessageRepository;
import com.ameda.works.chat_oscaris.requests.MessageRequest;
import com.ameda.works.chat_oscaris.responses.MessageResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final MessageMapper messageMapper;
    private final FileService fileService;


    public void saveMessage( MessageRequest request ){
        Chat chat = chatRepository.findById(request.getChatId())
                .orElseThrow(()-> new EntityNotFoundException("Chat not found"));
        Message message = new Message();
        message.setContent(request.getContent());
        message.setChat(chat);
        message.setSenderId(request.getSenderId());
        message.setReceiverId(request.getReceiverId());
        message.setMessageType(request.getMessageType());
        message.setState(MessageState.SENT);
        messageRepository.save(message);
        //todo notification...
    }

    public List<MessageResponse> findChatMessages(String chatId ){
        return messageRepository.findMessagesByChatId(chatId)
                .stream()
                .map(messageMapper::toMessageResponse)
                .toList();

    }

    @Transactional
    public void setMessagesToSeen(String chatId, Authentication currentUser ){
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(()-> new EntityNotFoundException("chat not found"));

        final String recipientId = getRecipientId(chat, currentUser);
        messageRepository.setMessagesToSeenByChatId(chatId, MessageState.SEEN);
    }

    private String getRecipientId(Chat chat, Authentication currentUser) {
        if ( chat.getSender().equals(currentUser.getName()) ){
            return chat.getRecipient().getId();
        }
        //todo notification
        return  chat.getSender().getId();
    }

    public void uploadMediaMessage(String chatId, MultipartFile file, Authentication currentUser ){
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(()-> new EntityNotFoundException("Chat with passed ID could not be found."));
        final String senderId = getSenderId(chat,currentUser);
        final String receiverId = getReceiverId(chat,currentUser);

        final  String filePath = fileService.saveFile(file,senderId);
        Message message = new Message();
        message.setChat(chat);
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setMessageType(MessageType.IMAGE);
        message.setState(MessageState.SENT);
        message.setMediaFilePath(filePath);
        messageRepository.save(message);
        //todo notification

    }

    private String getReceiverId(Chat chat, Authentication currentUser) {
        if( chat.getRecipient().getId().equals(currentUser.getName()) ){
            return chat.getRecipient().getId();
        }
        return null;
    }

    private String getSenderId(Chat chat, Authentication currentUser) {
        if( chat.getSender().getId().equals(currentUser.getName()) ){
            return chat.getSender().getId();
        }
        return null;
    }
}
