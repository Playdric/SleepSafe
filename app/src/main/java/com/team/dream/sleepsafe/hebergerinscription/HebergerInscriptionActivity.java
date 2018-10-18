package com.team.dream.sleepsafe.hebergerinscription;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.hebergerinformation.HebergerInformationActivity;

public class HebergerInscriptionActivity extends AppCompatActivity implements IHebergerInscriptionActivity {

    private HebergerInscriptionActivityPresenter presenter;
    private Button btnConection;
    private EditText edtPseudo;
    private EditText edtPassword;
    private EditText edtPasswordConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heberger_inscription);
        presenter = new HebergerInscriptionActivityPresenter(this, this);

        initView();

        initListeners();
    }

    private void initListeners() {

        btnConection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.registration(
                        edtPseudo.getText().toString(),
                        edtPassword.getText().toString(),
                        edtPasswordConfirmation.getText().toString());
            }
        });

    }

    private void initView() {
        this.btnConection = findViewById(R.id.btn_inscription);
        this.edtPseudo = findViewById(R.id.edt_pseudo);
        this.edtPassword = findViewById(R.id.edt_password);
        this.edtPasswordConfirmation = findViewById(R.id.edt_password_confirmation);
    }

    @Override
    public void errorFields(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void launchHebergerInfo() {
        startActivity(new Intent(HebergerInscriptionActivity.this, HebergerInformationActivity.class));
    }
}
