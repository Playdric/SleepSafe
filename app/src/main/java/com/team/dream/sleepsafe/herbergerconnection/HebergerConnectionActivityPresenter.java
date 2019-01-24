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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.team.dream.sleepsafe.BaseApplication;
import com.team.dream.sleepsafe.hebergerhome.HebergerHomeActivity;
import com.team.dream.sleepsafe.hebergerinformation.HebergerInformationActivity;
import com.team.dream.sleepsafe.hebergerinscription.HebergerInscriptionActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executor;

public class HebergerConnectionActivityPresenter implements IHebergerConnectionActivityPresenter {

    private IHebergerConnectionActivity view;
    private Context context;

    public HebergerConnectionActivityPresenter(@NonNull IHebergerConnectionActivity view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void connection(String email, String password) {

        if(email.isEmpty() || password.isEmpty()) {
            view.errorFields("Remplis tous les champs");
            return ;
        }

        JSONObject user = new JSONObject();
        try {
            user.accumulate("email", email);
            System.out.println(password);
            user.accumulate("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("tag", "signInWithEmail:success");
                            BaseApplication.id_user = task.getResult().getUser().getUid();
                            view.launchHome();
                        } else {
                            view.errorFields("Une erreur est survenu lors de la connexion (authentification)");
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                        }
                    }
                });

    }
}
