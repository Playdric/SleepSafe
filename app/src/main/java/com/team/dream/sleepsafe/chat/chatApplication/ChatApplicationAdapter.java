package com.team.dream.sleepsafe.chat.chatApplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.chat.entity.Messages;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ChatApplicationAdapter extends RecyclerView.Adapter {

    private List<Messages> messagesList;
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private FirebaseAuth mAuth;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Messages message = (Messages) messagesList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }

    public ChatApplicationAdapter(List<Messages> messages) {
        this.messagesList = messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageHolder(itemView);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageHolder(itemView);
        }

        return null;
    }

    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        Messages message = messagesList.get(position);
        mAuth = FirebaseAuth.getInstance();
        if (message.getFrom().getId().equals(mAuth.getCurrentUser().getUid())) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }
}
