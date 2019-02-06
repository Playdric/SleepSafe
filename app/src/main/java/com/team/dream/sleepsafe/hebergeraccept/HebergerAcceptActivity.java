package com.team.dream.sleepsafe.hebergeraccept;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.team.dream.sleepsafe.BaseApplication;
import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.hebergeraccept.adapter.SinisterAdapter;
import com.team.dream.sleepsafe.hebergeraccept.model.Accommodation;
import com.team.dream.sleepsafe.hebergeraccept.model.Sinister;
import com.team.dream.sleepsafe.hebergerinformation.HebergerInformationActivity;
import com.team.dream.sleepsafe.hebergeuraccommodationlist.HebergerAccommodationListActivity;

import org.json.JSONObject;

import java.util.ArrayList;

public class HebergerAcceptActivity extends AppCompatActivity  implements IHebergerAcceptActivity{

    public RecyclerView recyclerView;

    private HebergerAcceptActivityPresenter presenter;

    private ArrayList<Sinister> sinisters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heberger_accept);



        presenter = new HebergerAcceptActivityPresenter(this, this);

        presenter.getData();

        presenter = new HebergerAcceptActivityPresenter(this, this);

        /// test
        initView();
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
                .setMessage("Sinistré : " + sinister.getSurname() + " " + sinister.getName() + "\n\nTéléphone :  " + String.valueOf(sinister.getPhoneNumber()) + "\n\nNombre de personne(s) :  " + sinister.getNbPeople() + "\n\nCommentaire :  " + sinister.getComment())

                .setPositiveButton("Affecter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i=new Intent(HebergerAcceptActivity.this,HebergerAccommodationListActivity.class);
                        i.putExtra("sinister", sinister.getIdPhone());
                        startActivity(i);
                    }
                })
                .setNegativeButton("Retour", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void initView() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("sinister")
                .whereEqualTo("status", 0)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("firstname", document.getString("firstname"));
                                Log.d("lastname", document.getString("lastname"));
                                Log.d("phone_number", document.getLong("phone_number").toString());
                                Log.d("nb_people", document.getLong("nb_people").toString());
                                Log.d("comment", document.getString("comment"));
                                Log.d("localisation", document.getString("localisation"));
                                Log.d("id_phone", document.getString("id_phone"));

                                sinisters.add(new Sinister(document.getString("firstname"),document.getString("lastname"), document.getLong("phone_number").intValue(), document.getLong("nb_people").intValue(),document.getString("comment"),document.getString("localisation"),document.getString("id_phone")));
                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }
                            fillData(sinisters);
                            /*
                            accommodationBtn = findViewById(R.id.btn_new_accommodation);
                            sinisterBtn = findViewById(R.id.btn_check_sinister);
                            items_view_accommodation = findViewById(R.id.items_view_accommodation);
                            initListener();
                            */
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
