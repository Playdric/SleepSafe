package com.team.dream.sleepsafe.chatApplication;

import android.os.Bundle;
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
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team.dream.sleepsafe.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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

        initView();
        initListener();

        getPreviousMessage();
    }

    private void initListener() {
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitMessageToBDD();
            }
        });
    }

    private void initView() {
        sendBtn = findViewById(R.id.ButtonSend);
        inputMsg = findViewById(R.id.InputMessage);
    }

    private void submitMessageToBDD() {
        try {
            String url = urlLocal + "chat/post";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("userID", "1");
            jsonBody.put("message", inputMsg.getText().toString());
            jsonBody.put("timestamp", "2847298");

            final String mRequestBody = jsonBody.toString();

            StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject toto = new JSONObject(response);
                        Chat chat = new Chat(toto);
                        chatList.add(chat);
                        mAdapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, error.getMessage());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                        return null;
                    }
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(ChatApplicationActivity.this);

            requestQueue.add(jsonObjectRequest);
            mAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void getPreviousMessage() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlLocal + "chat/array", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
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
                    Toast.makeText(ChatApplicationActivity.this,
                            "AuthFailureError",
                            Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    Log.e(TAG, "ERROR : "+ Integer.toString(error.networkResponse.statusCode) + " " + error.getMessage());
                    Toast.makeText(ChatApplicationActivity.this,
                            "ServerError",
                            Toast.LENGTH_LONG).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(ChatApplicationActivity.this,
                            "NetworkError",
                            Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(ChatApplicationActivity.this,
                            "ParseError",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        queue.add(jsonArrayRequest);
    }

}
