package com.team.dream.sleepsafe.hebergerdispo;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.team.dream.sleepsafe.BaseApplication;

import org.json.JSONException;
import org.json.JSONObject;


public class HebergerDispoActivityPresenter implements IHebergerDispoActivityPresenter {

    private IHebergerDispoActivity view;
    private Context context;

    public HebergerDispoActivityPresenter( IHebergerDispoActivity view, Context context){
        this.context = context;
        this.view = view;
    }

    @Override
    public void acceptHost(String id_host, String id_phone) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id_host", id_host);
            jsonObject.put("id_phone", id_phone);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        view.openCall();
        /*
        AndroidNetworking.put(BaseApplication.BASE_URL + "/sinister")
                .addJSONObjectBody(jsonObject)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        view.sendOK(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        view.sendError(anError);
                    }
                });
                */
    }
}
