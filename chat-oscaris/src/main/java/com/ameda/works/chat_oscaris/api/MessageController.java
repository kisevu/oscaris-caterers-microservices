package com.ameda.works.chat_oscaris.api;


import com.ameda.works.chat_oscaris.requests.MessageRequest;
import com.ameda.works.chat_oscaris.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMessage(@RequestBody MessageRequest request){
        messageService.saveMessage(request);
    }

    @PostMapping(value = "/upload-media", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMessage(
            @RequestParam("chat-id") String chatId,
            //todo - parameter from swagger
            @RequestParam("file") MultipartFile file,
            Authentication currentUser
    ){
        messageService.uploadMediaMessage(chatId,file,currentUser);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void setMessageToSeen(
            @RequestParam("chat-id") String chatId,
            Authentication currentUser
    ){
        messageService.setMessagesToSeen(chatId,currentUser);
    }

    @GetMapping("/chat/{chat-id}")
    public ResponseEntity<?> getMessages(
            @PathVariable("name") String chatId
    ){
        return ResponseEntity.ok(messageService.findChatMessages(chatId));
    }
}
