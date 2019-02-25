package com.team.dream.sleepsafe.chat.chatApplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;
import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.chat.chatApplication.entity.Messages;
import com.team.dream.sleepsafe.chat.chatApplication.entity.Users;
import com.team.dream.sleepsafe.messagerie.Messagerie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatApplicationActivity extends AppCompatActivity {

    final private String TAG = "Chat_Context";
    private Button sendBtn;
    private EditText inputMsg;
    private FirebaseFirestore db;
    private Messagerie messagerie;
    private FirebaseAuth mAuth;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_application);

        db = FirebaseFirestore.getInstance();
        messagerie = new Messagerie();
        mAuth = FirebaseAuth.getInstance();

        initView();
        initListener();

        Bundle myIntent = getIntent().getExtras(); // gets the previously created intent


        if (myIntent != null) {
            String id = myIntent.getString("id");
            if (id != null) {
                messagerie.setId(id);
                db.collection("chat").document(id)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                refreshMessages(documentSnapshot);
                            }
                        }
                    });

                final DocumentReference docRef = db.collection("chat").document(messagerie.getId());
                docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot snapshot,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }

                        if (snapshot != null && snapshot.exists()) {
                            Log.d(TAG, "Current data: " + snapshot.getData());
                            refreshMessages(snapshot);
                        } else {
                            Log.d(TAG, "Current data: null");
                        }
                    }
                });
            }
        }
    }

    private void initListener() {
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewMessage();
            }
        });
    }

    private void initView() {
        sendBtn = findViewById(R.id.ButtonSend);
        inputMsg = findViewById(R.id.InputMessage);
        mRecyclerView = (RecyclerView) findViewById(R.id.chat);
    }

    void initMessageList(List<Messages> messages) {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ChatApplicationAdapter(messages);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void createNewMessage() {
        String content = inputMsg.getText().toString();
        Users from = new Users(mAuth.getUid(), mAuth.getCurrentUser().getEmail());
        Users to = new Users(messagerie.getPartner().getId(), messagerie.getPartner().getPseudo());

        Messages message = new Messages(from, to, content);

        Map<String, List<Messages>> data = new HashMap<>();

        messagerie.getMessages().add(message);

        data.put("messages", messagerie.getMessages());

        db.collection("chat").document(messagerie.getId())
                .set(data, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                        inputMsg.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                        Toast.makeText(ChatApplicationActivity.this, "Une erreur s'est produite", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void refreshMessages(DocumentSnapshot data) {
        messagerie = data.toObject(Messagerie.class);
        List<Messages> messages = messagerie.getMessages();
        initMessageList(messages);
    }
}
