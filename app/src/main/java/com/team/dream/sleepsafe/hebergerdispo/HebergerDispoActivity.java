package com.team.dream.sleepsafe.hebergerdispo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.team.dream.sleepsafe.R;

public class HebergerDispoActivity extends AppCompatActivity implements IHebergerDispoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heberger_dispo);
    }
}
