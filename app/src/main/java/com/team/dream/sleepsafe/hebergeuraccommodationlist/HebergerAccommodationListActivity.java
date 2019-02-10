package com.team.dream.sleepsafe.hebergeuraccommodationlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.team.dream.sleepsafe.BaseApplication;
import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.hebergeraccept.HebergerAcceptActivity;
import com.team.dream.sleepsafe.hebergeraccept.HebergerAcceptActivityPresenter;
import com.team.dream.sleepsafe.hebergeraccept.adapter.AccommodationAdapter;
import com.team.dream.sleepsafe.hebergeraccept.adapter.AccommodationAdapterList;
import com.team.dream.sleepsafe.hebergeraccept.model.Accommodation;
import com.team.dream.sleepsafe.hebergeraccept.model.Sinister;
import com.team.dream.sleepsafe.hebergeraccomodation.HebergerAccommodationActivity;
import com.team.dream.sleepsafe.hebergeraccomodation.IHebergerAccommodationActivity;
import com.team.dream.sleepsafe.hebergerhome.HebergerHomeActivity;
import com.team.dream.sleepsafe.hebergerinformation.HebergerInformationActivity;

import java.util.ArrayList;

public class HebergerAccommodationListActivity extends AppCompatActivity implements IHebergerAccommodationListActivity{

    RecyclerView items_view_accommodation;
    private HebergerAccommodationListActivityPresenter presenter;
    private ArrayList<Accommodation> accommodations = new ArrayList<Accommodation>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heberger_accommodation_list);
        presenter = new HebergerAccommodationListActivityPresenter( this, this);
        initView((Integer) getIntent().getExtras().get("sinister_bed"));
    }

    @Override
    public void fillData(ArrayList<Accommodation> accommodations) {
        AccommodationAdapterList adapter = new AccommodationAdapterList(accommodations, this);
        items_view_accommodation = findViewById(R.id.items_view_accommodationList);
        items_view_accommodation.setLayoutManager(new LinearLayoutManager(this));
        items_view_accommodation.setItemAnimator(new DefaultItemAnimator());
        items_view_accommodation.setAdapter(adapter);
    }

    private void initListener() {


    }


    public void doSmth() {

        Intent i=new Intent(HebergerAccommodationListActivity.this,HebergerHomeActivity.class);
        startActivity(i);
    }

    private void initView(final Integer sinister_bed) {
        Log.d("aaaaaaaaaaaaaaaaaaaab", sinister_bed.toString());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("accomodation")
                .whereEqualTo("id_user", BaseApplication.id_user)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(Integer.parseInt(document.getString("nb_bed")) >= sinister_bed)
                                    accommodations.add(new Accommodation(document.getId(), document.getString("address_name"),document.getString("address_city"), Integer.parseInt(document.getString("address_zipcode")), Integer.parseInt(document.getString("nb_bed"))));
                                Log.d("TAG ID ACCOMMODATION :", document.getId());
                            }
                            fillData(accommodations);
                            items_view_accommodation = findViewById(R.id.items_view_accommodation);
                            initListener();
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }


}
