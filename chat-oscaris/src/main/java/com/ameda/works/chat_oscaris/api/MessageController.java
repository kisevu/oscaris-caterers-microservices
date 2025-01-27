package com.ameda.works.chat_oscaris.api;


import com.ameda.works.chat_oscaris.requests.MessageRequest;
import com.ameda.works.chat_oscaris.services.MessageService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
@Tag(name = "Message")
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
            @Parameter()
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
