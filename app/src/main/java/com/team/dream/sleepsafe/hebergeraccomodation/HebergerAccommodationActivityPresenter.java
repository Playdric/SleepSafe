package com.team.dream.sleepsafe.hebergeraccomodation;

import android.content.Context;

import com.team.dream.sleepsafe.hebergerhome.IHebergerHomeActivity;

public class HebergerAccommodationActivityPresenter {

    private IHebergerAccommodationActivity view;
    private Context context;

    public HebergerAccommodationActivityPresenter( IHebergerAccommodationActivity view, Context context){
        this.context = context;
        this.view = view;
    }
}
