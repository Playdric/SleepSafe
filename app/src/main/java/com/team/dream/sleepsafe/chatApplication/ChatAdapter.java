package com.team.dream.sleepsafe.chatApplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team.dream.sleepsafe.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    private List<Chat> chatList;
    final private String TAG = "Chat_Context";

    public ChatAdapter(List<Chat> chatList) {
        this.chatList = chatList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView pseudo, message, date;

        public MyViewHolder(View view) {
            super(view);
            pseudo = (TextView) view.findViewById(R.id.pseudo);
            message = (TextView) view.findViewById(R.id.message);
            date = (TextView) view.findViewById(R.id.date);
        }
    }

    @NonNull
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.MyViewHolder holder, int position) {
        Chat chat = chatList.get(position);
        try {
            holder.pseudo.setText(chat.getPseudo());
            holder.message.setText(chat.getMessage());
            holder.date.setText(chat.getDate());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }
}
