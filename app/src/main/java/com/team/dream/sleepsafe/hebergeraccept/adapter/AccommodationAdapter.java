package com.team.dream.sleepsafe.hebergeraccept.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.hebergeraccept.HebergerAcceptActivity;
import com.team.dream.sleepsafe.hebergeraccept.model.Accommodation;
import com.team.dream.sleepsafe.hebergeraccept.model.Sinister;
import com.team.dream.sleepsafe.hebergeraccomodation.HebergerAccommodationActivity;
import com.team.dream.sleepsafe.hebergeraccomodation.HebergerAccommodationActivityPresenter;
import com.team.dream.sleepsafe.hebergerhome.HebergerHomeActivity;
import com.team.dream.sleepsafe.hebergerinformation.HebergerInformationActivity;

import java.util.ArrayList;

public class AccommodationAdapter extends RecyclerView.Adapter<AccommodationAdapter.MyViewHolder> {

    HebergerHomeActivity activity;

    private ArrayList<Accommodation> accommodations = new ArrayList<>();

    public AccommodationAdapter(ArrayList<Accommodation> accommodations, HebergerHomeActivity activity) {
        this.accommodations = accommodations;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AccommodationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.accommodation_item, parent, false);

        return new AccommodationAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AccommodationAdapter.MyViewHolder holder, int position) {
        final Accommodation accommodation = accommodations.get(position);
        holder.adress.setText(accommodation.getAddress() + " " + accommodation.getZipcode() + " " + accommodation.getCity() );

        holder.btnSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.doSmth(accommodation);
            }
        });
    }

    @Override
    public int getItemCount() {
        return accommodations.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView adress, zipcode;
        public Button btnSeeMore;

        public MyViewHolder(View view) {
            super(view);
            adress = view.findViewById(R.id.txv_address);
            zipcode = view.findViewById(R.id.txv_zipcode);
            btnSeeMore = view.findViewById(R.id.btn_accept);

        }
    }
}
