package com.team.dream.sleepsafe.hebergerinscription;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.team.dream.sleepsafe.BaseApplication;

import org.json.JSONObject;

public class HebergerInscriptionActivityPresenter implements IHebergerInscriptionActivityPresenter {

    private IHebergerInscriptionActivity view;
    private Context context;

    public HebergerInscriptionActivityPresenter(IHebergerInscriptionActivity view, Context context){
        this.view = view;
        this.context = context;
    }

    @Override
    public void registration(String pseudo, String password, String passwordConfirmation) {
        if(pseudo.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
            view.errorFields("Remplis tous les champs");
            return;
        }
        if (!TextUtils.equals(password, passwordConfirmation)) {
            view.errorFields("Les deux mots de passe ne correspondent pas");
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
                        // do anything with response
                    }
                    @Override
                    public void onError(ANError error) {
                        view.errorFields(error.getErrorBody());
                    }
                });

        view.launchHebergerInfo();
    }
}
