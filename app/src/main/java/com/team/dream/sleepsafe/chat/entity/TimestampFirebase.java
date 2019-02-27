package com.team.dream.sleepsafe.chat.entity;

public class TimestampFirebase {
    private Integer nanoseconds;
    private Integer seconds;

    public TimestampFirebase(){}
    public TimestampFirebase(Integer nanoseconds, Integer seconds) {
        this.nanoseconds = nanoseconds;
        this.seconds = seconds;
    }

    public Integer getNanoseconds() {
        return nanoseconds;
    }

    public void setNanoseconds(Integer nanoseconds) {
        this.nanoseconds = nanoseconds;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public Integer getTimestamp() {
        return seconds * 1000;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }
}
