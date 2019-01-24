package com.team.dream.sleepsafe.newsinister;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team.dream.sleepsafe.BaseApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class NewSinisterActivityPresenter implements INewSinisterActivityPresenter {

    private INewSinisterActivity view;
    private Context context;

    public NewSinisterActivityPresenter( INewSinisterActivity view, Context context){
        this.context = context;
        this.view = view;
    }


    @Override
    public void sendSinister( String name, String surname, int phoneNumber, int nb, String comm, String localisation, String id_phone) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> sinister = new HashMap<>();
        sinister.put("name", name);
        sinister.put("surname", surname);
        sinister.put("phone_number", phoneNumber);
        sinister.put("nb_people", nb);
        sinister.put("comment", comm);
        sinister.put("localisation", localisation);
        sinister.put("id_phone", id_phone);

        db.collection("sinister")
                .add(sinister)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("tag", "DocumentSnapshot added with ID: success :" + documentReference.getId());
                        view.launchHome();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("tag", "DocumentSnapshot added with ID: error :" + e.getMessage());
                        view.errorFields("Une erreur est survenu lors de la déclaration d'un sinistre (firebase)");
                    }
                });
    }
}
