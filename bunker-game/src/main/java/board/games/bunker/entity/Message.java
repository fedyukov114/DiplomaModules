package board.games.bunker.entity;

import board.games.bunker.enums.MessageType;

import java.util.UUID;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "message")
public class Message {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "content")
    private String content;

    @Column(name = "sender")
    private String sender;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "type", nullable = false)
    @Enumerated(STRING)
    private MessageType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
