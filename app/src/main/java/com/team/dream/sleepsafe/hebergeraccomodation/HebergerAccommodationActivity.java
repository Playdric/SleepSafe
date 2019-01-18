package com.team.dream.sleepsafe.hebergeraccomodation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.hebergeraccept.HebergerAcceptActivity;
import com.team.dream.sleepsafe.hebergeraccept.model.Accommodation;
import com.team.dream.sleepsafe.hebergerhome.HebergerHomeActivity;
import com.team.dream.sleepsafe.hebergerinformation.HebergerInformationActivity;
import com.team.dream.sleepsafe.herbergerconnection.HebergerConnectionActivity;
import com.team.dream.sleepsafe.homescreen.MainActivity;

public class HebergerAccommodationActivity extends AppCompatActivity implements IHebergerAccommodationActivity{

    Button backBtn;
    Button confirmBtn;
    TextView address;
    TextView zipcode;
    TextView city;
    TextView nbPlaces;

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

        backBtn = findViewById(R.id.btn_back);
        confirmBtn = findViewById(R.id.btn_confirm);

        /*
        address.setText(accommodation.getAddress());
        zipcode.setText(accommodation.getZipcode());
        city.setText(accommodation.getCity());
        nbPlaces.setText(Integer.valueOf(accommodation.getNb_bed()));
        */

    }

}
