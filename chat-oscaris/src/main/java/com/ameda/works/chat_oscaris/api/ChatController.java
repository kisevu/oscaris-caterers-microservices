package com.ameda.works.chat_oscaris.api;


import com.ameda.works.chat_oscaris.responses.CustomResponse;
import com.ameda.works.chat_oscaris.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatController {


    private final ChatService chatService;

    @PostMapping("/create")
    public ResponseEntity<CustomResponse> createChat(
            @RequestParam(name = "sender-id") String senderId,
            @RequestParam(name ="receiver-id") String receiverId
    ){
        final String chatId = chatService.createChat(senderId,receiverId);
        var response = CustomResponse.builder()
                .response(chatId)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/chats")
    public ResponseEntity<?> getChatsByReceiver(Authentication currentUser ){
        return new ResponseEntity<>(chatService.getchatsByReceiverId(currentUser),HttpStatus.OK);
    }

}
