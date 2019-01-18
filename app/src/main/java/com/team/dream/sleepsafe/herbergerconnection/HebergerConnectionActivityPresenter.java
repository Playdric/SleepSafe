package com.team.dream.sleepsafe.herbergerconnection;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.team.dream.sleepsafe.BaseApplication;
import com.team.dream.sleepsafe.hebergerhome.HebergerHomeActivity;
import com.team.dream.sleepsafe.hebergerinformation.HebergerInformationActivity;

import org.json.JSONException;
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
       /*
        if(pseudo.isEmpty() || password.isEmpty()) {
            view.errorFields("Remplis tous les champs");
            return;
        }

        JSONObject user = new JSONObject();
        try {
            user.accumulate("email", pseudo);
            System.out.println(password);
            user.accumulate("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        AndroidNetworking.post(BaseApplication.BASE_URL+ "/")
                .addJSONObjectBody(user)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("connection response:", response.toString());
                        SharedPreferences sp = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor e = sp.edit();
                        try {
                            JSONObject user = response.getJSONObject("user");
                            e.putString("id_user", user.getString("id"));
                            e.apply();
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        view.launchHebergerInfo();
                    }
                    @Override
                    public void onError(ANError error) {
                        view.errorFields(error.getErrorBody());
                    }
                });
*/

    }
}
