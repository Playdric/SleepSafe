package com.team.dream.sleepsafe;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.google.firebase.firestore.FirebaseFirestore;

public class BaseApplication extends Application {

    public static String BASE_URL = "http://192.168.43.113:3030";
    public static final String FCM_ID = "FCM_ID";

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
