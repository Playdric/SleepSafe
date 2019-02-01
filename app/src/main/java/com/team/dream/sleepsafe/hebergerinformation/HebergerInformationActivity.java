package com.team.dream.sleepsafe.hebergerinformation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team.dream.sleepsafe.BaseApplication;
import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.hebergerhome.HebergerHomeActivity;
import com.team.dream.sleepsafe.hebergerhome.HebergerHomeActivityPresenter;
import com.team.dream.sleepsafe.hebergerinscription.HebergerInscriptionActivity;
import com.team.dream.sleepsafe.hebergerinscription.HebergerInscriptionActivityPresenter;
import com.team.dream.sleepsafe.herbergerconnection.HebergerConnectionActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HebergerInformationActivity extends AppCompatActivity implements IHebergerInformationActivity {

    private EditText edtSipCode;
    private EditText edtCity;
    private EditText edtRoad;
    private EditText edtMaxDistance;
    private EditText edtPlaces;
    private Button btnGeoloc;
    private Button btnValidate;
    private CheckBox chbTakeEm;

    private String idPhone;

    LocationManager locationManager;
    String provider;
    FusedLocationProviderClient mFusedLocationClient;

    private HebergerInformationActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heberger_information);
        presenter = new HebergerInformationActivityPresenter(this, this);

        Intent i = getIntent();
        idPhone = i.getStringExtra("sinister");

        initView();

        initListeners();
    }

    private void initView() {
        edtSipCode = findViewById(R.id.edt_zip_code);
        edtCity = findViewById(R.id.edt_city);
        edtRoad = findViewById(R.id.edt_road);
        btnGeoloc = findViewById(R.id.btn_i_live_here);
        btnValidate = findViewById(R.id.btn_continue);
        edtPlaces = findViewById(R.id.edt_places_dispo);
        edtMaxDistance = findViewById(R.id.edt_max_distance);
        chbTakeEm = findViewById(R.id.chb_take_em);

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


    private void initListeners() {
        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendHost(
                        edtSipCode.getText().toString(),
                        edtCity.getText().toString(),
                        edtRoad.getText().toString(),
                        edtPlaces.getText().toString(),
                        edtMaxDistance.getText().toString(),
                        chbTakeEm.getText().toString());
            }
        });
    }

    public void getLoc() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this)
                        .setTitle("coucou")
                        .setMessage("ttt")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(HebergerInformationActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        3);
                            }
                        })
                        .create()
                        .show();
            } else {
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

    @Override
    public void errorFields(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void launchHome() {
        startActivity(new Intent(HebergerInformationActivity.this, HebergerHomeActivity.class));
    }
}


