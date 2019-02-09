package com.team.dream.sleepsafe.chatApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class City {


    private String name;
    private String state;
    private String country;
    private boolean capital;
    private long population;
    private List<String> regions;

    public City() {}

    public City(String name, String state, String country, boolean capital, long population, List<String> regions) {
        // ...
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public boolean isCapital() {
        return capital;
    }

    public long getPopulation() {
        return population;
    }

    public List<String> getRegions() {
        return regions;
    }

}

public class Channel {
    private List<Number> userId;
    private List<Messages> messages = new ArrayList<>();

    public Channel() { }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }

    public List<Number> getUserId() {
        return userId;
    }

    public void setUserId(List<Number> userId) {
        this.userId = userId;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void newMessage(Messages message) {
        this.messages.add(message);
    }
}

class Messages {
    private Number from;
    private Number to;
    private String content;
    private String timestamp;

    public Messages() { }

    public Messages(User from, User to, String content) {
        this.from = from.getId();
        this.to = to.getId();
        this.content = content;
    }

    public void setTo(Number to) {
        this.to = to;
    }

    public Number getTo() {
        return to;
    }


    public Number getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from.id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

class User {
    Number id;
    String pseudo;
    String email;

    public User() { }

    public User(Number id, String pseudo, String email) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}