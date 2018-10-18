package com.team.dream.sleepsafe.hebergerinformation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.team.dream.sleepsafe.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class HebergerInformationActivity extends AppCompatActivity implements IHebergerInformationActivity {

    private EditText edtPseudo;
    private EditText edtSipCode;
    private EditText edtCity;
    private EditText edtRoad;
    private EditText edtMail;
    private EditText edtPhone;
    private EditText edtMaxDistance;
    private Button btnGeoloc;
    private Button btnValidate;
    private CheckBox chbTakeEm;

    LocationManager locationManager;
    String provider;
    FusedLocationProviderClient mFusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heberger_information);

        initView();
    }

    private void initView() {
        edtSipCode = findViewById(R.id.edt_zip_code);
        edtCity = findViewById(R.id.edt_city);
        edtRoad = findViewById(R.id.edt_road);
        btnGeoloc = findViewById(R.id.btn_i_live_here);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        btnGeoloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoc();
            }
        });
    }

    public void getLoc() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("coucou")
                        .setMessage("ttt")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(HebergerInformationActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        3);
                            }
                        })
                        .create()
                        .show();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        3);
            }
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
                            Log.d("location:", location.toString());
                            Geocoder geocoder;
                            List<Address> addresses;
                            geocoder = new Geocoder(HebergerInformationActivity.this, Locale.getDefault());
                            try {
                                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                edtRoad.setText(addresses.get(0).getAddressLine(0).substring(0,addresses.get(0).getAddressLine(0).indexOf(','))); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                                edtCity.setText(addresses.get(0).getLocality());
                                String state = addresses.get(0).getAdminArea();
                                String country = addresses.get(0).getCountryName();
                                edtSipCode.setText(addresses.get(0).getPostalCode());
                                String knownName = addresses.get(0).getFeatureName();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }
}


