package com.team.dream.sleepsafe.herbergerconnection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.hebergerinformation.HebergerInformationActivity;
import com.team.dream.sleepsafe.hebergerinscription.HebergerInscriptionActivity;

public class HebergerConnectionActivity extends AppCompatActivity implements IHebergerConnectionActivity{

    private Button btnConnection;
    private Button btnSignIn;
    private EditText edtEmail;
    private EditText edtPassword;

    private HebergerConnectionActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heberger_connection);
        presenter = new HebergerConnectionActivityPresenter(this, this);
        initView();
        initListeners();
    }

    private void initView() {
        btnConnection = findViewById(R.id.btn_connexion);
        btnSignIn = findViewById(R.id.btn_inscription);
        edtEmail = findViewById(R.id.edt_pseudo);
        edtPassword = findViewById(R.id.edt_password);
    }

    private void initListeners() {
        btnConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.connection(edtEmail.getText().toString(), edtPassword.getText().toString());
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HebergerConnectionActivity.this, HebergerInscriptionActivity.class));
            }
        });
    }

    @Override
    public void errorFields(String error) {

        Toast.makeText(this, error, Toast.LENGTH_LONG).show();

    }

    @Override
    public void launchHebergerInfo() {
        finishAndRemoveTask();
        Toast.makeText(this, "Vous pouvez quitter l'application.", Toast.LENGTH_SHORT).show();
    }
}
