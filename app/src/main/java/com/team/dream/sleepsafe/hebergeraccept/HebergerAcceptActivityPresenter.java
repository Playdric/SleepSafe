package com.team.dream.sleepsafe.hebergeraccept;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.team.dream.sleepsafe.BaseApplication;
import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.hebergeraccept.model.Accommodation;
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


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("sinister")
                .whereEqualTo("status", 0)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Sinister> sinisters = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                sinisters.add(new Sinister(document.getId(), document.getString("firstname"), document.getString("lastname"), document.getLong("phone_number").intValue(), document.getLong("nb_people").intValue(), document.getString("comment"), document.getString("localisation"), document.getString("id_phone")));
                                Log.d("TAG ID ACCOMMODATION :", document.getId());
                            }
                            Log.d("c", "c");
                            view.fillData(sinisters);
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
