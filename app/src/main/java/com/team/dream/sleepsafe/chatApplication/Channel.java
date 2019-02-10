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
    private Object userId;
    private List<Object> messages;

    public Channel() { }

    public Channel(Object id) {
        id = this.userId;
    }

    public void setMessages(List<Object> messages) {
        this.messages = messages;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
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