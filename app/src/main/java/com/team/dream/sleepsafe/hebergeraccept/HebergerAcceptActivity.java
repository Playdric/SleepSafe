package com.team.dream.sleepsafe.hebergeraccept;

import android.content.DialogInterface;
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
    }

    @Override
    public void fillData(ArrayList<Sinister> sinisters) {
        SinisterAdapter adapter = new SinisterAdapter(sinisters, this);
        recyclerView = findViewById(R.id.rcv_sinister);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void doSmth(Sinister sinister) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat_Light_Dialog))
                .setTitle("details")
                .setMessage(sinister.getComment())
                .setPositiveButton("accepter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.validateSinister();
                        Toast.makeText(HebergerAcceptActivity.this, "on a bien pris en compte, tu peux fermer l'app", Toast.LENGTH_SHORT).show();
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
