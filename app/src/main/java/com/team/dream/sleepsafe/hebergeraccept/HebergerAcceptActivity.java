package com.team.dream.sleepsafe.hebergeraccept;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.hebergeraccept.adapter.SinisterAdapter;
import com.team.dream.sleepsafe.hebergeraccept.model.Sinister;
import com.team.dream.sleepsafe.hebergerinformation.HebergerInformationActivity;

import org.json.JSONObject;

import java.util.ArrayList;

public class HebergerAcceptActivity extends AppCompatActivity  implements IHebergerAcceptActivity{

    public RecyclerView recyclerView;

    private ArrayList<Sinister> sinisters = new ArrayList<>();

    private HebergerAcceptActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heberger_accept);



        presenter = new HebergerAcceptActivityPresenter(this, this);

        presenter.getData();

        presenter = new HebergerAcceptActivityPresenter(this, this);

        /// test
        ArrayList<Sinister> sinisters = new ArrayList<>();
        sinisters.add(new Sinister("paul", "finet", 0606060606, 3, "commentaire test", "rue test", "id_phone test"));
        fillData(sinisters);
        ////////////////////////
    }

    @Override
    public void fillData(ArrayList<Sinister> sinisters) {
        SinisterAdapter adapter = new SinisterAdapter(sinisters, this);
        recyclerView = findViewById(R.id.rcv_sinister);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void doSmth(final Sinister sinister) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat_Light_Dialog))
                .setTitle("details")
                .setMessage("Sinistré : " + sinister.getSurname() + " " + sinister.getName() + "\n\n Téléphone :  " + String.valueOf(sinister.getPhoneNumber()) + "\n\n Commentaire :  " + sinister.getComment())

                .setPositiveButton("accepter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i=new Intent(HebergerAcceptActivity.this,HebergerInformationActivity.class);
                        i.putExtra("sinister", sinister.getIdPhone());
                        startActivity(i);
                    }
                })
                .setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}
