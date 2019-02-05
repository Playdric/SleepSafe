package com.team.dream.sleepsafe.hebergeraccept.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.team.dream.sleepsafe.R;
import com.team.dream.sleepsafe.hebergeraccept.HebergerAcceptActivity;
import com.team.dream.sleepsafe.hebergeraccept.model.Sinister;
import com.team.dream.sleepsafe.hebergeraccomodation.HebergerAccommodationActivity;

import java.util.ArrayList;

public class SinisterAdapterList extends RecyclerView.Adapter<SinisterAdapterList.MyViewHolder> {

    HebergerAccommodationActivity activity;

    private ArrayList<Sinister> sinisters = new ArrayList<>();

    public SinisterAdapterList(ArrayList<Sinister>  sinisters, HebergerAccommodationActivity activity) {
        this.sinisters = sinisters;
        this.activity = activity;
    }

    @NonNull
    @Override
    public SinisterAdapterList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sinister_item, parent, false);

        return new SinisterAdapterList.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull  SinisterAdapterList.MyViewHolder holder, int position) {
        final Sinister sinister = sinisters.get(position);
        holder.name.setText(sinister.getSurname() + " " + sinister.getName());
        holder.nbPeople.setText(activity.getApplicationContext().getString(R.string.nb_people, sinister.getNbPeople()));
        holder.btnSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.doSmth(sinister);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sinisters.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, nbPeople;
        public Button btnSeeMore;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.txv_name);
            nbPeople = view.findViewById(R.id.txv_people);
            btnSeeMore = view.findViewById(R.id.btn_accept);
            btnSeeMore.setText("supprimer");

        }
    }
}
