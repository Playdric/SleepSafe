package com.team.dream.sleepsafe.hebergeraccomodation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.hebergerhome.HebergerHomeActivity;

public class HebergerAccommodationActivity extends AppCompatActivity implements IHebergerAccommodationActivity{

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
    }

}
