package com.team.dream.sleepsafe.chat.chatApplication.entity;

import java.util.HashMap;
import java.util.List;

public class Channel {
    private Object userId;
    private List<Object> messages;

    public Channel() { }

    public Channel(Object id) {
        id = this.userId;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public void setMessages(List<Object> messages) {
        this.messages = messages;
    }

    public List<Object> getMessages() {
        return messages;
    }
}

class IsView {
    String userId;
    HashMap view;

    public IsView(String userId, HashMap view) {
        this.userId = userId;
        this.view = new HashMap(2);
    }
}