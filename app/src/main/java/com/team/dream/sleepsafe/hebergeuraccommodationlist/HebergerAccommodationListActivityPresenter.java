package com.team.dream.sleepsafe.hebergeuraccommodationlist;

import android.content.Context;

import com.team.dream.sleepsafe.hebergeraccomodation.IHebergerAccommodationActivity;
import com.team.dream.sleepsafe.hebergerhome.IHebergerHomeActivity;

public class HebergerAccommodationListActivityPresenter implements IHebergerAccommodationListActivityPresenter {

    private IHebergerAccommodationListActivity view;
    private Context context;

    public HebergerAccommodationListActivityPresenter(IHebergerAccommodationListActivity view, Context context){
        this.context = context;
        this.view = view;
    }

}
