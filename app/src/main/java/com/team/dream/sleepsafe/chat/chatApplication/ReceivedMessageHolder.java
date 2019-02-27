package com.team.dream.sleepsafe.chat.chatApplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.chat.entity.Messages;

public class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView pseudo, content, date;
        ImageView profileImage;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.text_message_body);
            date = (TextView) itemView.findViewById(R.id.text_message_time);
            pseudo = (TextView) itemView.findViewById(R.id.text_message_name);
            profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
        }

        void bind(Messages message) {
            content.setText(message.getContent());

            // Format the stored timestamp into a readable String using method.
            date.setText((new TimestampToDate().getDate(message.getTimestamp().getSeconds())));
            pseudo.setText(message.getFrom().getPseudo());

            // Insert the profile image from the URL into the ImageView.
            // Utils.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profileImage);
        }
}
