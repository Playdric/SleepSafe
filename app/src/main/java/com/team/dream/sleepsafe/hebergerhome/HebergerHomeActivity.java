package com.team.dream.sleepsafe.hebergerhome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.hebergeraccept.HebergerAcceptActivity;
import com.team.dream.sleepsafe.hebergerinformation.HebergerInformationActivity;


public class HebergerHomeActivity extends AppCompatActivity implements IHebergerHomeActivity{

    RecyclerView items_view_accommodation;
    Button accommodationBtn;
    Button sinisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heberger_home);

        initView();
        initListener();
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
