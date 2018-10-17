package com.team.dream.sleepsafe;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
