package com.team.dream.sleepsafe.hebergerdispo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.team.dream.sleepsafe.R;

import org.json.JSONObject;

public class HebergerDispoActivity extends AppCompatActivity implements IHebergerDispoActivity {

    TextView hostName;
    TextView hostAdress;
    TextView hostPhone;
    Button acceptBtn;
    SharedPreferences sharedPreferences;
    HebergerDispoActivityPresenter presenter;

    String address = "000000";
    String phoneNumber = "000000";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heberger_dispo);

        Intent i = getIntent();
        address = i.getStringExtra("address");
        phoneNumber = i.getStringExtra("phone");

        initView();
        initListener();
        getContact();
    }

    private void initListener() {
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presenter.acceptHost("id_host","id_phone");
                openCall();
            }
        });
    }

    private void initView() {
        hostName = findViewById(R.id.title_heberger);
        hostAdress = findViewById(R.id.adress_heberger);
        hostAdress.setText(address);
        hostPhone = findViewById(R.id.phone_heberger);
        hostPhone.setText(phoneNumber);
        acceptBtn = findViewById(R.id.btn_validate_heberger);
        presenter    = new HebergerDispoActivityPresenter(this , this);

    }

    @Override
    public void sendOK(final JSONObject response) {
        AlertDialog alertDialog = new AlertDialog.Builder(HebergerDispoActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Nous avons pris en compte votre acceptation");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        HebergerDispoActivity.this.finish();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void sendError(ANError error) {
        AlertDialog alertDialog = new AlertDialog.Builder(HebergerDispoActivity.this).create();
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

    public void openMaps(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Oui pour être rediriger sur Maps Non pour rester sur les coordonnées");
        builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                Uri gmmIntentUri = Uri.parse("geo:0,0?q= " + hostAdress.getText().toString());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("NON", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void openCall() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Oui pour appeler");
        builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", hostPhone.getText().toString(), null));
                startActivity(intent);
            }
        });
        builder.setNegativeButton("NON", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }



    public void getContact() {
        Intent i = getIntent();
        if (i != null) {
            Bundle b = i.getExtras();
            if (b != null) {
                hostPhone.setText(b.getString("phone"));
                hostAdress.setText(b.getString("address"));
            }
        }
        /*if il y a des données de notif push
        hostPhone.setText(phone);
        hostAdress.setText(adresse);
        hostName.setText(name + "peut vous héberger !");
        else {
            hostPhone.setText(sharedPreferences.getString("phone","0600000000"));
            hostAdress.setText(sharedPreferences.getString("adress",""));
            hostName.setText("On peut vous héberger !");
        }*/
    }
}
