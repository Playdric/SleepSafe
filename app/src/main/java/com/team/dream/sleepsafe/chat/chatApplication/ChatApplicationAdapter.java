package com.team.dream.sleepsafe.chat.chatApplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.chat.chatApplication.entity.Messages;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ChatApplicationAdapter extends RecyclerView.Adapter<ChatApplicationAdapter.MyViewHolder> {

    private List<Messages> messagesList;

    public ChatApplicationAdapter(List<Messages> messages) {
        this.messagesList = messages;
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
    public ChatApplicationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatApplicationAdapter.MyViewHolder holder, int position) {
        Messages message = messagesList.get(position);
        try {
            holder.pseudo.setText(message.getFrom().getPseudo());
            holder.message.setText(message.getContent());
            holder.date.setText(new TimestampToDate().getDate(message.getTimestamp().getSeconds()));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }
}
