package com.team.dream.sleepsafe.hebergerdispo;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.team.dream.sleepsafe.MyFireBaseMessagingService;
import com.team.dream.sleepsafe.R;

import org.json.JSONException;
import org.json.JSONObject;

public class HebergerDispoActivity extends AppCompatActivity implements IHebergerDispoActivity {

    TextView hostName;
    TextView hostAdress;
    TextView hostPhone;
    Button acceptBtn;
    HebergerDispoActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heberger_dispo);


        initView();
        initListener();
    }

    private void initListener() {
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.acceptHost("id_host","id_phone");
            }
        });
    }

    private void initView() {
        hostName = findViewById(R.id.title_heberger);
        hostAdress = findViewById(R.id.adress_heberger);
        hostPhone = findViewById(R.id.phone_heberger);
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

    public void openCall(View v) {
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
}
