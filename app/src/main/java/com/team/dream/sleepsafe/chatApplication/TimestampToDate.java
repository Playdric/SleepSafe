package com.team.dream.sleepsafe.chatApplication;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampToDate {

    public TimestampToDate() {
    }

    public String getDate(String timestamp) {
        Timestamp ts = new Timestamp(Long.parseLong(timestamp));
        Date date = new Date(ts.getTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}
