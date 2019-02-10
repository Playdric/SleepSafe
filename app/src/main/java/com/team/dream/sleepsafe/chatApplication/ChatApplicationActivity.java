package com.team.dream.sleepsafe.chatApplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;
import com.team.dream.sleepsafe.R;

public class ChatApplicationActivity extends AppCompatActivity {

    final private String TAG = "Chat_Context";
    private Button sendBtn;
    private EditText inputMsg;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_application);

        db = FirebaseFirestore.getInstance();

        initView();
        initListener();

        Bundle myIntent = getIntent().getExtras(); // gets the previously created intent


        if (myIntent != null) {
            String id = myIntent.getString("id");
            Log.d(TAG, id);
        }
    }

    private void initListener() {
    }

    private void initView() {
        sendBtn = findViewById(R.id.ButtonSend);
        inputMsg = findViewById(R.id.InputMessage);
    }

}
