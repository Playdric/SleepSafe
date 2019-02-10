package com.team.dream.sleepsafe.messagerie;

import com.google.firebase.Timestamp;
import com.team.dream.sleepsafe.chatApplication.Messages;
import com.team.dream.sleepsafe.chatApplication.Users;

import java.util.List;

public class Messagerie {
    private String id;
    private List<String> userId;
    private List<Messages> messages;
    private List<Object> isView;
    private List<Object> isTyping;
    private Users partner;

    public Messagerie() { }

    public Messagerie(List<String> userId) {
        this.userId = userId;
    }

    public List<String> getUserId() {
        return userId;
    }

    public void setUserId(List<String> userId) {
        this.userId = userId;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }

    public List<Object> getIsView() {
        return isView;
    }

    public void setIsView(List<Object> isView) {
        this.isView = isView;
    }

    public List<Object> getIsTyping() {
        return isTyping;
    }

    public void setIsTyping(List<Object> isTyping) {
        this.isTyping = isTyping;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Users getPartner() {
        return partner;
    }

    public void setPartner(Users partner) {
        this.partner = partner;
    }

    // Custom GETTER & SETTTER
    public String getLastContent(){
        return this.messages.get(this.messages.size() - 1).getContent();
    }

    public Timestamp getLastTimestamp(){
        return this.messages.get(this.messages.size() - 1).getTimestamp();
    }
}


