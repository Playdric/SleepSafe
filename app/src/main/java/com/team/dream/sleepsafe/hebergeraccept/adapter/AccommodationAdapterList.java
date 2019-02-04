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
import com.team.dream.sleepsafe.hebergerhome.HebergerHomeActivity;
import com.team.dream.sleepsafe.hebergerinformation.HebergerInformationActivity;
import com.team.dream.sleepsafe.hebergeuraccommodationlist.HebergerAccommodationListActivity;
import com.team.dream.sleepsafe.homescreen.MainActivity;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

public class AccommodationAdapterList extends RecyclerView.Adapter<AccommodationAdapterList.MyViewHolder>{

    HebergerAccommodationListActivity activity;

    private ArrayList<Accommodation> accommodations = new ArrayList<>();

    public AccommodationAdapterList(ArrayList<Accommodation> accommodations, HebergerAccommodationListActivity activity) {
        this.accommodations = accommodations;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AccommodationAdapterList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.accommodation_item, parent, false);

        return new AccommodationAdapterList.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AccommodationAdapterList.MyViewHolder holder, int position) {
        final Accommodation accommodation = accommodations.get(position);
        holder.adress.setText(accommodation.getAddress() + " " + accommodation.getZipcode() + " " + accommodation.getCity() );
        holder.btnSeeMore.setText("Affectez");
        holder.btnSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // affecter le logement sur lequel on à cliqué



                activity.doSmth();
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
