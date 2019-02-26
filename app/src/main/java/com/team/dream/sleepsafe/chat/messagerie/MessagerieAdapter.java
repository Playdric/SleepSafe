package com.team.dream.sleepsafe.chat.messagerie;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.chat.chatApplication.TimestampToDate;
import com.team.dream.sleepsafe.chat.chatApplication.entity.Messagerie;

import java.util.List;

public class MessagerieAdapter extends RecyclerView.Adapter<MessagerieAdapter.MyViewHolder> {
    private List<Messagerie> chatList;
    final private String TAG = "Chat_Context";
    private static View.OnClickListener mClickListener;

    public MessagerieAdapter(List<Messagerie> chatList) {
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

    public static void setClickListener(View.OnClickListener callback) {
        mClickListener = callback;
    }
    @NonNull
    @Override
    public MessagerieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_list_row, parent, false);

        MessagerieAdapter.MyViewHolder holder = new MyViewHolder(itemView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onClick(view);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessagerieAdapter.MyViewHolder holder, int position) {
        Messagerie messagerie = chatList.get(position);
        try {
            holder.pseudo.setText(messagerie.getPartner().getPseudo());
            holder.message.setText(messagerie.getLastContent());
            holder.date.setText(new TimestampToDate().getDate(messagerie.getLastTimestamp()));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public Messagerie getItem(int position) {
        return chatList.get(position);
    }
}
