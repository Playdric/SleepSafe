package com.team.dream.sleepsafe.hebergeraccomodation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.hebergeraccept.adapter.AccommodationAdapter;
import com.team.dream.sleepsafe.hebergeraccept.adapter.SinisterAdapter;
import com.team.dream.sleepsafe.hebergeraccept.adapter.SinisterAdapterList;
import com.team.dream.sleepsafe.hebergeraccept.model.Accommodation;
import com.team.dream.sleepsafe.hebergeraccept.model.Sinister;
import com.team.dream.sleepsafe.hebergerhome.HebergerHomeActivity;

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
                startActivity(new Intent(HebergerAccommodationActivity.this, HebergerHomeActivity.class));
            }
        });

    }

    public void doSmth(Sinister sinister) {

        // il faut supprimer le sinister du logement en bdd

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


        // recup sinisters liée au logement via accommodationId et les ajoutés au tableau sinisters
        sinisters.add(new Sinister("paul","finet",223344, 22,"test comment", "test location","éez"));
        sinisters.add(new Sinister("toto","tutu",223344, 22,"test comment", "test location","éez"));



        fillData(sinisters);
    }

}
