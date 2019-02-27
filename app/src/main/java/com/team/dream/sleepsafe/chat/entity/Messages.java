package com.team.dream.sleepsafe.chat.entity;

import com.google.firebase.Timestamp;

public class Messages {
    private Users from;
    private Users to;
    private String content;
    private TimestampFirebase timestamp;

    public Messages() { }

    public Messages(Users from, Users to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
        this.timestamp = new TimestampFirebase(Timestamp.now().getNanoseconds(), (int)Timestamp.now().getSeconds());
    }

    public Messages(Users from, Users to, String content, TimestampFirebase timestamp) {
        this.from = from;
        this.to = to;
        this.content = content;
        this.timestamp = timestamp;
    }

    public void setTo(Users to) {
        this.to = to;
    }

    public Users getTo() {
        return to;
    }

    public Users getFrom() {
        return from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TimestampFirebase getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(TimestampFirebase timestamp) {
        this.timestamp = timestamp;
    }

    public void setFrom(Users from) {
        this.from = from;
    }
}
