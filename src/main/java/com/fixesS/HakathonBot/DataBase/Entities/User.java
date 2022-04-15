package com.fixesS.HakathonBot.DataBase.Entities;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "state_id")
    private int stateId;

    @Column(name = "last_message")
    private Integer lastMessageId;

    public Long getId() {
        return id;
    }

    public Integer getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(Integer lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public String toString() {
        return "User\n{" +
                "\nid=" + id +
                ",\nchatId=" + chatId +
                ",\nstateId=" + stateId +
                ",\nlastMessageId=" + lastMessageId +
                "\n}";
    }
}
