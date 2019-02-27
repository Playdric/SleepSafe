package com.team.dream.sleepsafe.chat.contactList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.chat.entity.Users;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyViewHolder> {
    final private String TAG = "Contact_list";
    private static View.OnClickListener mClickListener;
    private List<Users> usersList;

    public ContactListAdapter(List<Users> users) { this.usersList = users; }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView pseudo;

        public MyViewHolder(View view) {
            super(view);
            pseudo = (TextView) view.findViewById(R.id.pseudo);
        }
    }

    public static void setClickListener(View.OnClickListener callback) {
        mClickListener = callback;
    }
    @NonNull
    @Override
    public ContactListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_row, parent, false);

        ContactListAdapter.MyViewHolder holder = new MyViewHolder(itemView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onClick(view);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListAdapter.MyViewHolder holder, int position) {
        Users users = usersList.get(position);
        try {
            holder.pseudo.setText(users.getPseudo());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public Users getItem(int position) {
        return usersList.get(position);
    }
}
