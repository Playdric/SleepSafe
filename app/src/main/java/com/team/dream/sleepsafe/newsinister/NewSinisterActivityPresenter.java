package com.team.dream.sleepsafe.newsinister;
import android.content.Context;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.team.dream.sleepsafe.BaseApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class NewSinisterActivityPresenter implements INewSinisterActivityPresenter {

    private INewSinisterActivity view;
    private Context context;

    public NewSinisterActivityPresenter( INewSinisterActivity view, Context context){
        this.context = context;
        this.view = view;
    }


    @Override
    public void sendSinister(String pseudo, int nb, String comm) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pseudo", pseudo);
            jsonObject.put("nb", nb);
            jsonObject.put("comm", comm);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.post(BaseApplication.URL)
                .addJSONObjectBody(jsonObject)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                    }
                    @Override
                    public void onError(ANError error) {
                        view.sendError(error);
                    }
                });
    }

}
