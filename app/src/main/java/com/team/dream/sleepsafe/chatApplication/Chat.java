package com.team.dream.sleepsafe.chatApplication;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Chat {
    private String pseudo, message, date;
    final private String TAG = "Chat_Context";

    public Chat(JSONObject chat) {
        try {
            this.pseudo = chat.getString("userID");
            this.message = chat.getString("message");
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
