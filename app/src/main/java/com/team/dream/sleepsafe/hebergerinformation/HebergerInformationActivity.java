package com.team.dream.sleepsafe.hebergerinformation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.team.dream.sleepsafe.R;

public class HebergerInformationActivity extends AppCompatActivity implements IHebergerInformationActivity{

    private EditText edtPseudo;
    private EditText edtSipCode;
    private EditText edtCity;
    private EditText edtMail;
    private EditText edtPhone;
    private EditText edtMaxDistance;
    private Button btnValidate;
    private CheckBox chbTakeEm;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heberger_information);
    }
}
