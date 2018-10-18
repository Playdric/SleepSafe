package com.team.dream.sleepsafe.hebergeraccept;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.team.dream.sleepsafe.BaseApplication;
import com.team.dream.sleepsafe.hebergeraccept.model.Sinister;
import com.team.dream.sleepsafe.hebergerinformation.IHebergerInformationActivity;
import com.team.dream.sleepsafe.hebergerinformation.IHebergerInformationActivityPresenter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HebergerAcceptActivityPresenter implements IHebergerAcceptActivityPresenter{

    private IHebergerAcceptActivity view;
    private Context context;

    public HebergerAcceptActivityPresenter(IHebergerAcceptActivity view, Context context) {
        this.view = view;
        this.context = context;
    }


    @Override
    public void getData() {
        AndroidNetworking.get(BaseApplication.BASE_URL + "/sinister/current")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            ArrayList<Sinister> sinisters = new ArrayList<>();
                            // do anything with response
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject o = response.getJSONObject(i);
                                sinisters.add(new Sinister(o.getInt("nb_people"), o.getString("comment"), o.getString("localisation"), o.getString("id_phone")));
                            }

                            view.fillData(sinisters);
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    @Override
    public void validateSinister() {
        AndroidNetworking.get(BaseApplication.BASE_URL + "/host/accept_sinister")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("error", anError.getErrorDetail());
                    }
                });
    }
}
