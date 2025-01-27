package com.ameda.works.chat_oscaris.entities.message;

import com.ameda.works.chat_oscaris.entities.chat.Chat;
import com.ameda.works.chat_oscaris.entities.common.BasedAuditor;
import com.ameda.works.chat_oscaris.entities.common.constants.MessageConstants;
import com.ameda.works.chat_oscaris.entities.enu.MessageState;
import com.ameda.works.chat_oscaris.entities.enu.MessageType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "messages")
@NamedQuery(name = MessageConstants.FIND_MESSAGES_BY_CHAT_ID,
query = "SELECT m FROM Message m WHERE m.chat.id = :chatId ORDER BY m.createdDate")
@NamedQuery(name = MessageConstants.SET_MESSAGES_TO_SEEN_BY_CHAT,
query="UPDATE Message SET state = :newState WHERE chat.id = :chatId ")
public class Message extends BasedAuditor {

    @Id
    @SequenceGenerator(name = "msg-seq", sequenceName = "msg-seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "msg-seq")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private  String content;

    @Enumerated(EnumType.STRING)
    private MessageState state;

    private MessageType messageType;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @Column(nullable = false,name = "sender_id")
    private String senderId;
    @Column(nullable = false, name = "receiver_id")
    private String receiverId;
    private String mediaFilePath;

}
