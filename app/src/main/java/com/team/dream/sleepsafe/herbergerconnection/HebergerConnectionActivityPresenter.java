package com.team.dream.sleepsafe.herbergerconnection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.team.dream.sleepsafe.BaseApplication;

import org.json.JSONObject;

public class HebergerConnectionActivityPresenter implements IHebergerConnectionActivityPresenter {

    private IHebergerConnectionActivity view;
    private Context context;

    public HebergerConnectionActivityPresenter(@NonNull IHebergerConnectionActivity view, Context context) {
        this.view = view;
        this.context = context;
    }




    @Override
    public void connection(String pseudo, String password) {
        if(pseudo.isEmpty() || password.isEmpty()) {
            view.errorFields("Remplis tous les champs");
            return;
        }

        AndroidNetworking.post(BaseApplication.BASE_URL)
                .addBodyParameter("firstname", "Amit")
                .addBodyParameter("lastname", "Shekhar")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        view.launchHebergerInfo();
                    }
                    @Override
                    public void onError(ANError error) {
                        view.errorFields(error.getErrorBody());
                    }
                });

        view.launchHebergerInfo();
    }
}
