package com.team.dream.sleepsafe.hebergerdispo;

import com.androidnetworking.error.ANError;

import org.json.JSONObject;

public interface IHebergerDispoActivity {
    void sendOK(JSONObject response);
    void sendError(ANError error);
}
