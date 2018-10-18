package com.team.dream.sleepsafe.newsinister;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.homescreen.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class NewSinisterActivity extends AppCompatActivity implements INewSinisterActivity {

    Spinner spinner;
    Button mValidatebtn;
    EditText pseudo;
    EditText comm;

    NewSinisterActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sinister);
        AndroidNetworking.initialize(getApplicationContext());


        initView();
        initListener();
    }

    private void initListener() {
        mValidatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifySinisterInformation()){
                    presenter.sendSinister(
                            pseudo.getText().toString(),
                            spinner.getSelectedItemPosition(),
                            comm.getText().toString());
                }
            }
        });
    }

    private void initView() {
        mValidatebtn = findViewById(R.id.btn_validate_sinister);
        spinner      = findViewById(R.id.spinner_nb_people);
        pseudo       = findViewById(R.id.new_sinister_pseudo);
        comm         = findViewById(R.id.new_sinister_comm);
        presenter    = new NewSinisterActivityPresenter(this , this);

        List spinnerList = new ArrayList();
        for (int i = 0 ; i < 10 ; i++){
            spinnerList.add(Integer.toString(i));
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


    @Override
    public void sendError(ANError error) {
        AlertDialog alertDialog = new AlertDialog.Builder(NewSinisterActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Erreur : " + error + "Veuillez réessayer plus tard");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private Boolean verifySinisterInformation(){
        if (pseudo.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Renseignez un pseudo", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (spinner.getSelectedItemPosition() == -1) {
            Toast.makeText(this, "Renseignez le nombre de sinistrés", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }
}
