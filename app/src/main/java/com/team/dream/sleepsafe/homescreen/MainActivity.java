package com.team.dream.sleepsafe.homescreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.herbergerconnection.HebergerConnectionActivity;
import com.team.dream.sleepsafe.chat.messagerie.MessagerieActivity;
import com.team.dream.sleepsafe.newsinister.NewSinisterActivity;


public class MainActivity extends AppCompatActivity implements IMainActivity{

    Button mSiniterButton;
    Button btnHeberger;
    Button btnMessagerie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        if (i != null) {
            Bundle b = i.getExtras();
        }
        initView();
        initListener();


    }

    private void initListener() {
        mSiniterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewSinisterActivity.class));
            }
        });

        btnHeberger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HebergerConnectionActivity.class));
            }
        });

        btnMessagerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    // Users is signed in
                    startActivity(new Intent(MainActivity.this, MessagerieActivity.class));
                } else {
                    // No user is signed in
                    startActivity(new Intent(MainActivity.this, HebergerConnectionActivity.class));
                }
            }
        });
    }

    private void initView() {
        mSiniterButton  = findViewById(R.id.btn_stricken);
        btnHeberger     = findViewById(R.id.btn_host);
        btnMessagerie   = findViewById(R.id.btn_msg);
    }
}
