package com.team.dream.sleepsafe.homescreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.team.dream.sleepsafe.R;

public class MainActivity extends AppCompatActivity implements IMainActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
