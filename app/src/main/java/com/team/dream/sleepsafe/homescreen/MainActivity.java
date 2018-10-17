package com.team.dream.sleepsafe.homescreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.hebergerreceived.HebergerReceivedActivity;

public class MainActivity extends AppCompatActivity implements IMainActivity{

    Button mSiniterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSiniterButton = findViewById(R.id.btn_stricken);

        mSiniterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HebergerReceivedActivity.class);
                startActivity(intent);
            }
        });
    }
}
