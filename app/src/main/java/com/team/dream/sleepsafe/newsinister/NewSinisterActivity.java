package com.team.dream.sleepsafe.newsinister;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.team.dream.sleepsafe.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewSinisterActivity extends AppCompatActivity implements INewSinisterActivity {

    Button mValidatebtn;
    EditText comm;
    SharedPreferences sharedPreferences;
    NewSinisterActivityPresenter presenter;
    private EditText edtNbPeople;
    private EditText edtName;
    private EditText edtSurname;
    private EditText edtPhoneNumber;

    LocationManager locationManager;
    String provider;
    FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sinister);

        initView();
        initListener();
        getLoc();
    }

    private void initListener() {
        mValidatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewSinisterActivity.this, "Localisation pas trouvée, déso", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        mValidatebtn = findViewById(R.id.btn_validate_sinister);
        comm = findViewById(R.id.new_sinister_comm);
        presenter = new NewSinisterActivityPresenter(this, this);
        edtNbPeople = findViewById(R.id.tdt_nb_people);
        edtName = findViewById(R.id.tdt_name);
        edtSurname = findViewById(R.id.tdt_surname);
        edtPhoneNumber = findViewById(R.id.tdt_phone);

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

    @Override
    public void sendOK(JSONObject response) {
        try {
            AlertDialog alertDialog = new AlertDialog.Builder(NewSinisterActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Nous avons pris en compte votre demande à l'adresse : "
                    + response.getString("localisation") + " Vous pouvez quitter l'application");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            NewSinisterActivity.this.finish();
                        }
                    });
            alertDialog.show();
        } catch (JSONException e) {
            Log.d("JSON error", e.toString());
        }
    }

    private void getLoc() {

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            new AlertDialog.Builder(this)
                    .setTitle("On a besoin de vous localiser")
                    .setMessage("Afin d'en informer votre future hébergeur")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //Prompt the user once explanation has been shown
                            ActivityCompat.requestPermissions(NewSinisterActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    3);
                        }
                    })
                    .create()
                    .show();
        } else {
            getLocation();
        }


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 3: {
                getLocation();
            }
        }
    }
    @SuppressLint("MissingPermission")
    private void getLocation() {

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            String loc = "{latitude: " + location.getLatitude() + ", longitude: " + location.getLongitude() + "}";
                            Log.d("adresse : ", loc);
                            localisationFound(loc);
                        }
                    }
                });
    }

    private void localisationFound(final String loc) {
        mValidatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendSinister(
                        edtName.getText().toString(),
                        edtSurname.getText().toString(),
                        Integer.valueOf(edtPhoneNumber.getText().toString()),
                        Integer.valueOf(edtNbPeople.getText().toString()),
                        comm.getText().toString(),
                        loc,
                        getIdPhone());
            }
        });

    }

    private String getIdPhone() {
        sharedPreferences = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        return sharedPreferences.getString("FCM_ID","0");
    }
}
