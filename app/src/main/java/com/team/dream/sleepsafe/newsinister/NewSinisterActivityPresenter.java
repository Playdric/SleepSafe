package com.team.dream.sleepsafe.newsinister;
import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
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
    public void sendSinister(String pseudo, int nb, String comm, String localisation, String id_phone) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pseudo", pseudo);
            jsonObject.put("nb_people", nb);
            jsonObject.put("comment", comm);
            jsonObject.put("localisation", localisation);
            jsonObject.put("id_phone", id_phone);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("test",BaseApplication.URL + "/sinister");
        AndroidNetworking.post(BaseApplication.URL + "/sinister")
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
    }

}
