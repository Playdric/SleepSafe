package com.team.dream.sleepsafe.chatApplication;

import com.google.firebase.Timestamp;

public class Messages {
    private String from;
    private String to;
    private String content;
    private Timestamp timestamp;

    public Messages() { }

    public Messages(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }


    public String getFrom() {
        return from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
