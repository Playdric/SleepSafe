package com.team.dream.sleepsafe.herbergerconnection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.team.dream.sleepsafe.R;

public class HebergerConnectionActivity extends AppCompatActivity implements IHebergerConnectionActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heberger_connection);
    }
}
