package com.team.dream.sleepsafe;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class BaseApplication extends Application {

    public static String URL = "http://10.33.1.117:3000";

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
