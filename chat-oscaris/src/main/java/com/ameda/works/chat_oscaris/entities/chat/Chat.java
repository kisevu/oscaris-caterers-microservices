package com.ameda.works.chat_oscaris.entities.chat;

import com.ameda.works.chat_oscaris.entities.common.BasedAuditor;
import com.ameda.works.chat_oscaris.entities.common.constants.ChatConstants;
import com.ameda.works.chat_oscaris.entities.enu.MessageState;
import com.ameda.works.chat_oscaris.entities.enu.MessageType;
import com.ameda.works.chat_oscaris.entities.message.Message;
import com.ameda.works.chat_oscaris.entities.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table( name = "chats")

@NamedQuery(name = ChatConstants.FIND_CHAT_BY_SENDER_ID,
query = "SELECT DISTINCT c FROM Chats c WHERE c.sender.id = :senderId OR c.recipient.id = :senderId ORDER BY createdDate DESC")
@NamedQuery(name = ChatConstants.FIND_CHAT_BY_SENDER_ID_AND_RECEIVER_ID,
query = "SELECT DISTINCT c FROM Chats c WHERE (c.sender.id = :senderId AND c.recipient.id = :recipientId) OR  " +
        "(c.sender.id = :recipientId AND c.recipient.id = sender)")
public class Chat  extends BasedAuditor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    @OrderBy("createdDate DESC")
    private List<Message> messages;

    @Transient
    public String getChatName( String senderId ){
        if ( recipient.getId().equals(senderId) ){
            return sender.getFirstName() + " "+ sender.getLastName();
        }
        return recipient.getFirstName() + " "+ recipient.getLastName();
    }

    @Transient
    public  long getUnreadMessages(final String senderId){
        return messages.stream()
                .filter(m->m.getReceiverId().equals(senderId))
                .filter(m-> MessageState.SENT == m.getState())
                .count();
    }

    @Transient
    public String getLastMessage(){
        if ( messages !=null && !messages.isEmpty() ){
            if ( messages.get(0).getMessageType() != MessageType.TEXT ){
                return "Attachment";
            } else {
                return messages.get(0).getContent();
            }
        }
        return null;
    }

    @Transient
    public LocalDateTime getLastMessageTime(){
        if ( messages !=null && !messages.isEmpty() ){
            return messages.get(0).getCreatedDate();
        }
        return null;
    }
}
