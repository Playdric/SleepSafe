package com.team.dream.sleepsafe.chat.messagerie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.chat.chatApplication.ChatApplicationActivity;
import com.team.dream.sleepsafe.chat.chatApplication.entity.Messagerie;
import com.team.dream.sleepsafe.chat.chatApplication.entity.Users;
import com.team.dream.sleepsafe.chat.contactList.ContactListActivity;
import com.team.dream.sleepsafe.homescreen.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class MessagerieActivity extends AppCompatActivity {

    final private String TAG = "Messagerie_Context";
    private Users user1;
    private FirebaseFirestore db;
    TextView errorMessage;
    Button contactListBtn;
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

            initView();
            initListener();

            errorMessage.setText("Chargement...");
            getAllMessages();
        } else {
            startActivity(new Intent(MessagerieActivity.this, MainActivity.class));
        }
    }

    private void initView(){
        errorMessage = findViewById(R.id.error_message);
        contactListBtn = findViewById(R.id.newMsg);
        mRecyclerView = (RecyclerView) findViewById(R.id.chat);
    }

    private void initListener(){
        MessagerieAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = mRecyclerView.indexOfChild(v);
                Messagerie msg = ((MessagerieAdapter) mAdapter).getItem(pos);

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

        contactListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MessagerieActivity.this, ContactListActivity.class));
            }
        });
    }

    private void getAllMessages(){
        db.collection("chat")
            .whereArrayContains("userId", user1.getId())
            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e);
                        return;
                    }

                    errorMessage.setText("");
                    messagerieList.clear();

                    for (QueryDocumentSnapshot queryDocumentSnapshot : snapshot) {
                        Map<String, Object> data = queryDocumentSnapshot.getData();

                        Log.d(TAG, data.toString());
                        messagerieList.add(queryDocumentSnapshot.toObject(Messagerie.class));
                    }

                    if (messagerieList.isEmpty()) {
                        errorMessage.setText("Vous n'avez pas encore de message");
                        return;
                    }

                    initMessageList(messagerieList);
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
