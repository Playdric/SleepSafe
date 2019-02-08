package com.team.dream.sleepsafe.hebergeraccomodation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.hebergeraccept.adapter.AccommodationAdapter;
import com.team.dream.sleepsafe.hebergeraccept.adapter.SinisterAdapter;
import com.team.dream.sleepsafe.hebergeraccept.adapter.SinisterAdapterList;
import com.team.dream.sleepsafe.hebergeraccept.model.Accommodation;
import com.team.dream.sleepsafe.hebergeraccept.model.Sinister;
import com.team.dream.sleepsafe.hebergerhome.HebergerHomeActivity;
import com.team.dream.sleepsafe.newsinister.NewSinisterActivityPresenter;

import java.util.ArrayList;

public class HebergerAccommodationActivity extends AppCompatActivity implements IHebergerAccommodationActivity{

    RecyclerView items_view_sinisters;

    private ArrayList<Sinister> sinisters = new ArrayList<Sinister>();

    Button backBtn;
    Button confirmBtn;
    TextView address;
    TextView zipcode;
    TextView city;
    TextView nbPlaces;

    HebergerAccommodationActivityPresenter presenter;
    private String accommodationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heberger_accommodation);
        initView();
        initListener();
    }

    private void initListener() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HebergerAccommodationActivity.this, HebergerHomeActivity.class));
            }
        });
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("zzzzzzzzzzzzzzzzzzzz", address.getText().toString());
                presenter.modifyHebergerAccomodation(accommodationId, address.getText().toString(), zipcode.getText().toString(), city.getText().toString(), nbPlaces.getText().toString());
                startActivity(new Intent(HebergerAccommodationActivity.this, HebergerHomeActivity.class));
            }
        });

    }

    public void doSmth(Sinister sinister) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("sinister")
                .document(sinister.getId())
                .update("status",2);
    }

    public void fillData(ArrayList<Sinister> sinisters) {
        SinisterAdapterList adapter = new SinisterAdapterList(sinisters, this);
        items_view_sinisters = findViewById(R.id.items_view_sinisters);
        items_view_sinisters.setLayoutManager(new LinearLayoutManager(this));
        items_view_sinisters.setItemAnimator(new DefaultItemAnimator());
        items_view_sinisters.setAdapter(adapter);
    }

    private void initView() {

        Intent i = getIntent();

        presenter = new HebergerAccommodationActivityPresenter(this, this);

        backBtn = findViewById(R.id.btn_back);
        confirmBtn = findViewById(R.id.btn_confirm);
        address = findViewById(R.id.tdt_address);
        city = findViewById(R.id.tdt_city);
        nbPlaces = findViewById(R.id.tdt_nbplaces);
        zipcode = findViewById(R.id.tdt_zipcode);

        address.setText(i.getStringExtra("address"));
        zipcode.setText(i.getStringExtra("zipcode"));
        city.setText(i.getStringExtra("city"));
        nbPlaces.setText(i.getStringExtra("nb_total_places"));
        accommodationId = i.getStringExtra("accommodationId");
        Log.d("TAG ID :", accommodationId);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("sinister").whereEqualTo("id_accomodation", accommodationId).whereEqualTo("status", 1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("firstname", document.getString("firstname"));
                                Log.d("lastname", document.getString("lastname"));
                                Log.d("phone_number", document.getLong("phone_number").toString());
                                Log.d("nb_people", document.getLong("nb_people").toString());
                                Log.d("comment", document.getString("comment"));
                                Log.d("localisation", document.getString("localisation"));
                                Log.d("id_phone", document.getString("id_phone"));
                                Log.d("status", document.getLong("status").toString());
                                Log.d("id_accomodation", document.getString("id_accomodation"));

                                sinisters.add(new Sinister(document.getId(), document.getString("firstname"),document.getString("lastname"), document.getLong("phone_number").intValue(), document.getLong("nb_people").intValue(),document.getString("comment"),document.getString("localisation"),document.getString("id_phone")));
                                Log.d("TAG222", document.getId() + " => " + document.getData());
                            }
                            fillData(sinisters);
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });


        fillData(sinisters);
    }

}
