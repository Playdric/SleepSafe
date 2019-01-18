package com.team.dream.sleepsafe.hebergerhome;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.hebergeraccept.HebergerAcceptActivity;
import com.team.dream.sleepsafe.hebergeraccept.adapter.AccommodationAdapter;
import com.team.dream.sleepsafe.hebergeraccept.adapter.SinisterAdapter;
import com.team.dream.sleepsafe.hebergeraccept.model.Accommodation;
import com.team.dream.sleepsafe.hebergeraccept.model.Sinister;
import com.team.dream.sleepsafe.hebergerinformation.HebergerInformationActivity;

import java.util.ArrayList;


public class HebergerHomeActivity extends AppCompatActivity implements IHebergerHomeActivity{

    RecyclerView items_view_accommodation;

    private ArrayList<Sinister> accommodations = new ArrayList<>();
    Button accommodationBtn;
    Button sinisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heberger_home);

        /// test
        ArrayList<Accommodation> accommodations = new ArrayList<>();
        accommodations.add(new Accommodation("13 bis rue keller", "paris", 75011, 3, 123, 2));
        accommodations.add(new Accommodation("239 rue toto", "paris", 75013, 2, 1355, 3));
        fillData(accommodations);

        ////////////////////////
        initView();
        initListener();
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

    }

    private void initView() {

        accommodationBtn = findViewById(R.id.btn_new_accommodation);
        sinisterBtn = findViewById(R.id.btn_check_sinister);
        items_view_accommodation = findViewById(R.id.items_view_accommodation);
    }
}
