package com.team.dream.sleepsafe.messagerie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.chatApplication.Channel;
import com.team.dream.sleepsafe.chatApplication.Chat;
import com.team.dream.sleepsafe.chatApplication.ChatApplicationActivity;
import com.team.dream.sleepsafe.chatApplication.Users;
import com.team.dream.sleepsafe.homescreen.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessagerieActivity extends AppCompatActivity {

    final private String TAG = "Messagerie_Context";
    private Users user1;
    private FirebaseFirestore db;
    TextView errorMessage;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Messagerie> messagerieList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagerie);

        db = FirebaseFirestore.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user1 = new Users(user.getUid(), user.getDisplayName(), user.getEmail());
            getAllMessages();
            initView();
            initListener();
        } else {
            startActivity(new Intent(MessagerieActivity.this, MainActivity.class));
        }
    }

    private void initView(){
        errorMessage = findViewById(R.id.error_message);
        mRecyclerView = (RecyclerView) findViewById(R.id.chat);
    }

    private void initListener(){
        MessagerieAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = mRecyclerView.indexOfChild(v);
                Log.d(TAG, "POSITION : " + pos);
                Messagerie msg = ((MessagerieAdapter) mAdapter).getItem(pos);
                Log.d(TAG, msg.toString());

                Intent chatActivity = new Intent(MessagerieActivity.this, ChatApplicationActivity.class);
                String idChat = msg.getId();

                if (idChat == null) {
                    Toast.makeText(MessagerieActivity.this, "ERROR: Impossible de charger le chat", Toast.LENGTH_LONG).show();
                } else {
                    chatActivity.putExtra("id", idChat);
                    startActivity(chatActivity);
                }
            }
        });
    }

    private void getAllMessages(){
        db.collection("chat")
            .whereArrayContains("userId", user1.getId())
            .get()
            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    if (queryDocumentSnapshots.isEmpty()) {
                        errorMessage.setText("Vous n'avez aucun message privé");
                        return;
                    }

                    errorMessage.setText("");

                    for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                        Map<String, Object> data = queryDocumentSnapshot.getData();

                        Log.d(TAG, data.toString());
                        messagerieList.add(queryDocumentSnapshot.toObject(Messagerie.class));
                    }

                    initMessageList(messagerieList);
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w(TAG, "Error writing document", e);
                }
            });
    }

    void initMessageList(List<Messagerie> messagerieList) {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MessagerieAdapter(messagerieList);
        mRecyclerView.setAdapter(mAdapter);
    }

}
