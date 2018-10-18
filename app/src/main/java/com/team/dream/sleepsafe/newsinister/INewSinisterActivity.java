package com.team.dream.sleepsafe.newsinister;

import com.androidnetworking.error.ANError;

import org.json.JSONObject;

public interface INewSinisterActivity {

    void sendError(ANError error);
    void sendOK(JSONObject response);
}
