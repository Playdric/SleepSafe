package com.team.dream.sleepsafe.chatApplication;

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

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.team.dream.sleepsafe.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatApplicationActivity extends AppCompatActivity {

    final private String TAG = "Chat_Context";
    private Button sendBtn;
    private EditText inputMsg;
    private FirebaseFirestore db;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RequestQueue queue;
    private String urlLocal = "http://10.33.254.152:3002/";
    private List<Chat> chatList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_application);
        mRecyclerView = (RecyclerView) findViewById(R.id.chat);
        queue = Volley.newRequestQueue(this);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ChatAdapter(chatList);
        mRecyclerView.setAdapter(mAdapter);

        db = FirebaseFirestore.getInstance();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlLocal + "chat/array", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Chat chat = new Chat(jsonObject);
                        chatList.add(chat);
                        mAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(ChatApplicationActivity.this,
                            "error_network_timeout",
                            Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    //TODO
                    Toast.makeText(ChatApplicationActivity.this,
                            "AuthFailureError",
                            Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    //TODO
                    Log.e(TAG, "ERROR : "+ Integer.toString(error.networkResponse.statusCode) + " " + error.getMessage());
                    Toast.makeText(ChatApplicationActivity.this,
                            "ServerError",
                            Toast.LENGTH_LONG).show();
                } else if (error instanceof NetworkError) {
                    //TODO
                    Toast.makeText(ChatApplicationActivity.this,
                            "NetworkError",
                            Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    //TODO
                    Toast.makeText(ChatApplicationActivity.this,
                            "ParseError",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        initView();
        initListener();
        getMessages();
        queue.add(jsonArrayRequest);
    }

    private void initListener() {
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmitMessage();
            }
        });
    }

    private void initView() {
        sendBtn = findViewById(R.id.ButtonSend);
        inputMsg = findViewById(R.id.InputMessage);
    }

    private void onSubmitMessage() {
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

        db.collection("chat")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(ChatApplicationActivity.this, "User sended.",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ChatApplicationActivity.this, "Error user sended.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getMessages() {
        CollectionReference colRef = db.collection("chat");

        colRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                List<String> cities = new ArrayList<>();
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    if (doc.get("message") != null) {
                        String msg = doc.getString("message");
                        Log.d(TAG, msg);
                        cities.add(msg);
                    }
                }
            }
        });

    }

}
