package com.team.dream.sleepsafe.chat.chatApplication;

import com.google.firebase.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampToDate {

    public TimestampToDate() {
    }

    public String getDate(Integer timestamp) {
        Date dates = new Date();
        dates.setTime((long)timestamp * 1000);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return dateFormat.format(dates);
    }
}
