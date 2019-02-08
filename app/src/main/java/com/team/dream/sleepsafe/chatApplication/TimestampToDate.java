package com.team.dream.sleepsafe.chatApplication;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampToDate {

    private String timestamp;

    public TimestampToDate(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDate() {
        Timestamp ts = new Timestamp(Long.parseLong(timestamp));
        Date date = new Date(ts.getTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}
