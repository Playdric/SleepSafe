package com.team.dream.sleepsafe.homescreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.hebergerreceived.HebergerReceivedActivity;
import com.team.dream.sleepsafe.herbergerconnection.HebergerConnectionActivity;

public class MainActivity extends AppCompatActivity implements IMainActivity{

    Button mSiniterButton;
    Button btnHeberger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();


    }

    private void initListener() {
        mSiniterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HebergerReceivedActivity.class);
                startActivity(intent);
            }
        });

        btnHeberger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HebergerConnectionActivity.class));
            }
        });
    }

    private void initView() {
        mSiniterButton = findViewById(R.id.btn_stricken);
        btnHeberger = findViewById(R.id.btn_host);
    }
}
