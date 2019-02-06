package com.team.dream.sleepsafe.hebergerhome;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.StringValue;
import com.team.dream.sleepsafe.BaseApplication;
import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.hebergeraccept.HebergerAcceptActivity;
import com.team.dream.sleepsafe.hebergeraccept.adapter.AccommodationAdapter;
import com.team.dream.sleepsafe.hebergeraccept.model.Accommodation;
import com.team.dream.sleepsafe.hebergeraccomodation.HebergerAccommodationActivity;
import com.team.dream.sleepsafe.hebergerinformation.HebergerInformationActivity;
import com.team.dream.sleepsafe.hebergeuraccommodationlist.HebergerAccommodationListActivity;

import java.io.Console;
import java.util.ArrayList;


public class HebergerHomeActivity extends AppCompatActivity implements IHebergerHomeActivity{

    RecyclerView items_view_accommodation;

    private ArrayList<Accommodation> accommodations = new ArrayList<Accommodation>();
    Button accommodationBtn;
    Button sinisterBtn;
    Button msgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heberger_home);

        initView();
    }


    @Override
    public void fillData(ArrayList<Accommodation> accommodations) {
        AccommodationAdapter adapter = new AccommodationAdapter(accommodations, this);
        items_view_accommodation = findViewById(R.id.items_view_accommodation);
        items_view_accommodation.setLayoutManager(new LinearLayoutManager(this));
        items_view_accommodation.setItemAnimator(new DefaultItemAnimator());
        items_view_accommodation.setAdapter(adapter);
    }

    private void initListener() {
        accommodationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HebergerHomeActivity.this, HebergerInformationActivity.class));
            }
        });
        sinisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HebergerHomeActivity.this, HebergerAcceptActivity.class));
            }
        });
        msgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(HebergerHomeActivity.this, MessagingActivity.class));
            }
        });

    }

    public void doSmth(Accommodation accommodation) {


        Intent i= new Intent(HebergerHomeActivity.this,HebergerAccommodationActivity.class);
        i.putExtra("address",accommodation.getAddress());
        i.putExtra("city",accommodation.getCity());
        i.putExtra("zipcode",String.valueOf(accommodation.getZipcode()));
        i.putExtra("nb_total_places",String.valueOf(accommodation.getNb_bed()));
        i.putExtra("accommodationId",accommodation.getAccommodationId());

        startActivity(i);
    }

    private void initView() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("accomodation")
                .whereEqualTo("id_user", BaseApplication.id_user)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                accommodations.add(new Accommodation(document.getId(), document.getString("address_name"),document.getString("address_city"), Integer.parseInt(document.getString("address_zipcode")), Integer.parseInt(document.getString("nb_bed"))));
                            }
                            fillData(accommodations);
                            accommodationBtn = findViewById(R.id.btn_new_accommodation);
                            sinisterBtn = findViewById(R.id.btn_check_sinister);
                            items_view_accommodation = findViewById(R.id.items_view_accommodation);
                            initListener();
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
