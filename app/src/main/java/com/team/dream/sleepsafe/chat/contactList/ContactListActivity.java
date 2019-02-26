package com.team.dream.sleepsafe.chat.contactList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.chat.chatApplication.ChatApplicationActivity;
import com.team.dream.sleepsafe.chat.chatApplication.entity.Users;

import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    private String TAG = "Contact_List";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Users> usersList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        Log.d(TAG, "HEO");

        initView();
        initListener();

        createUser();
        initMessageList(usersList);
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.list_user);
    }
    private void initListener() {
        ContactListAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = mRecyclerView.indexOfChild(v);
                Users user = ((ContactListAdapter) mAdapter).getItem(pos);

                Intent chatActivity = new Intent(ContactListActivity.this, ChatApplicationActivity.class);
                chatActivity.putExtra("id", user.getId());
                chatActivity.putExtra("pseudo", user.getPseudo());
                chatActivity.putExtra("email", user.getEmail());
                chatActivity.putExtra("newConversation", "true");
                startActivity(chatActivity);
            }
        });
    }

    void initMessageList(List<Users> usersList) {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ContactListAdapter(usersList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void createUser() {
        usersList.add(new Users("1mYp1a7lfOgTNNcDv5BIq75btRq2", "Ruben", "ruben.desert@gmail.com"));
        usersList.add(new Users("WMqvawswbihWv5Eq0cBFjiRSV0a2", "Dorian", "d.alayrangues@gmail.com"));
        usersList.add(new Users("TFWPlnvCBpO4eLvC9MfCa7AYsRu2", "Paul", "paul.finet@gmail.com"));
        usersList.add(new Users("55yNgtI4yLRKQExE9OIyKtA41wi2", "Max", "dlaamy7@gmail.com"));
    }
}
