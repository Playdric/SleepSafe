package com.team.dream.sleepsafe.hebergeraccomodation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.team.dream.sleepsafe.hebergeraccept.model.Sinister;
import com.team.dream.sleepsafe.hebergerhome.IHebergerHomeActivity;

public class HebergerAccommodationActivityPresenter {

    private IHebergerAccommodationActivity view;
    private Context context;

    public HebergerAccommodationActivityPresenter( IHebergerAccommodationActivity view, Context context){
        this.context = context;
        this.view = view;
    }

    public void modifyHebergerAccomodation(String accommodationId, String address, String zipcode, String city, String nbPlaces){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference result = db.collection("accomodation")
                .document(accommodationId);
        result.update("address_name",address);
        result.update("address_zipcode  ",zipcode);
        result.update("address_city",city);
        result.update("nb_bed",nbPlaces);
    }
}
