package com.team.dream.sleepsafe.hebergerinformation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.team.dream.sleepsafe.R;

public class HebergerInformationActivity extends AppCompatActivity implements IHebergerInformationActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heberger_information);
    }
}
