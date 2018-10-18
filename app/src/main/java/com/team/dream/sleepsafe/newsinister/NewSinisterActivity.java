package com.team.dream.sleepsafe.newsinister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.team.dream.sleepsafe.R;

import java.util.ArrayList;
import java.util.List;

public class NewSinisterActivity extends AppCompatActivity implements INewSinisterActivity {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sinister);

        spinner = findViewById(R.id.spinner_nb_people);


        List spinnerList = new ArrayList();
        for (int i = 1 ; i < 10 ; i++){
            spinnerList.add(i);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
