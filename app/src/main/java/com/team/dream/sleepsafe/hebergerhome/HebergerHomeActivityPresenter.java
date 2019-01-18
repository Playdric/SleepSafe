package com.team.dream.sleepsafe.hebergerhome;

import android.content.Context;

import com.team.dream.sleepsafe.hebergerdispo.IHebergerDispoActivity;

public class HebergerHomeActivityPresenter implements IHebergerHomeActivityPresenter{

    private IHebergerHomeActivity view;
    private Context context;

    public HebergerHomeActivityPresenter( IHebergerHomeActivity view, Context context){
        this.context = context;
        this.view = view;
    }
}
