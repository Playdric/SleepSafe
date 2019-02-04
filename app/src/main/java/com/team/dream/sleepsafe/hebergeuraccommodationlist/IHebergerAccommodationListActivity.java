package com.team.dream.sleepsafe.hebergeuraccommodationlist;

import com.team.dream.sleepsafe.hebergeraccept.adapter.AccommodationAdapterList;
import com.team.dream.sleepsafe.hebergeraccept.model.Accommodation;
import com.team.dream.sleepsafe.hebergerinformation.HebergerInformationActivity;

import java.util.ArrayList;

public interface IHebergerAccommodationListActivity {

    void fillData(ArrayList<Accommodation> accommodations);
}
