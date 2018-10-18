package com.team.dream.sleepsafe.hebergerinscription;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.team.dream.sleepsafe.BaseApplication;
import com.team.dream.sleepsafe.R;

import org.json.JSONException;
import org.json.JSONObject;

public class HebergerInscriptionActivityPresenter implements IHebergerInscriptionActivityPresenter {

    private IHebergerInscriptionActivity view;
    private Context context;

    public HebergerInscriptionActivityPresenter(IHebergerInscriptionActivity view, Context context){
        this.view = view;
        this.context = context;
    }

    @Override
    public void registration(String email, String firstname, String lastname, String phoneNumber, String password, String passwordConfirmation) {
        if(email.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
            view.errorFields("Remplis tous les champs");
            return;
        }
        if (!TextUtils.equals(password, passwordConfirmation)) {
            view.errorFields("Les deux mots de passe ne correspondent pas");
            return;
        }

        SharedPreferences sharedPref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        String fcmId = sharedPref.getString(BaseApplication.FCM_ID, null);

        JSONObject user = new JSONObject();
        try {
            user.accumulate("id_phone", fcmId);
            user.accumulate("email", email);
            user.accumulate("firstname", firstname);
            user.accumulate("lastname", lastname);
            user.accumulate("phone_number", phoneNumber);
            user.accumulate("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("bite", user.toString());

        AndroidNetworking.post(BaseApplication.BASE_URL+ "/user/")
                .addJSONObjectBody(user)
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
    }
}
